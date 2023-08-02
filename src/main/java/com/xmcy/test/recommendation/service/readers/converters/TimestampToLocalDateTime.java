package com.xmcy.test.recommendation.service.readers.converters;

import com.opencsv.bean.AbstractBeanField;
import com.xmcy.test.recommendation.service.model.CryptoData;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimestampToLocalDateTime extends AbstractBeanField<CryptoData> {
    @Override
    protected Object convert(String s) {

        return LocalDateTime.ofEpochSecond(Long.parseLong(s) / 1000, 0, ZoneOffset.UTC);
    }
}