package com.xmcy.test.recommendation.service.readers.converters;

import com.opencsv.bean.AbstractBeanField;
import com.xmcy.test.recommendation.service.model.CryptoData;

import java.util.Calendar;

public class TimestampToCalendarConverter extends AbstractBeanField<CryptoData> {
    @Override
    protected Object convert(String s) {
        Calendar calData = Calendar.getInstance();
        calData.setTimeInMillis(Long.parseLong(s));
        return calData;
    }
}