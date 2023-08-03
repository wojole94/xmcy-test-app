package com.xmcy.test.recommendation.service.readers.converters;

import com.opencsv.bean.AbstractBeanField;
import com.xmcy.test.recommendation.service.exception.MalformedInputDataException;
import com.xmcy.test.recommendation.service.model.CryptoData;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimestampToLocalDateTime extends AbstractBeanField<CryptoData> {

    private static final String PARSING_ERROR_MESSAGE = "String in input cannot be parsed to correct date: %s";
    @Override
    protected Object convert(String s) {
        try {
            return LocalDateTime.ofEpochSecond(Long.parseLong(s) / 1000, 0, ZoneOffset.UTC);
        } catch (NumberFormatException ex){
            throw new MalformedInputDataException(String.format(PARSING_ERROR_MESSAGE, s), ex);
        }
    }
}