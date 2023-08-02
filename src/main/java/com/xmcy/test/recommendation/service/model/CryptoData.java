package com.xmcy.test.recommendation.service.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.xmcy.test.recommendation.service.readers.converters.TimestampToCalendarConverter;
import lombok.Builder;
import lombok.Data;

import java.util.Calendar;

@Data
@Builder
public class CryptoData {
    @CsvCustomBindByName(converter = TimestampToCalendarConverter.class)
    private Calendar timestamp;

    @CsvBindByName
    private String symbol;

    @CsvBindByName
    private Float price;

}
