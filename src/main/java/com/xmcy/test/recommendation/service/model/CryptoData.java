package com.xmcy.test.recommendation.service.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import com.xmcy.test.recommendation.service.readers.converters.TimestampToCalendarConverter;

import java.util.Calendar;

public class CryptoData {
    @CsvCustomBindByName(converter = TimestampToCalendarConverter.class)
    private Calendar timestamp;

    @CsvBindByName
    private String symbol;

    @CsvBindByName
    private Float price;

}
