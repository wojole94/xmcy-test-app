package com.xmcy.test.recommendation.service.service;

import com.xmcy.test.recommendation.service.model.CryptoData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataAnalysisServiceImplTest {

    DataAnalysisService daService;

    @BeforeEach
    void init(){
        daService = new DataAnalysisServiceImpl();
    }

    @Test
    void getMaxPrice_whenDataAvailable_selectMaxPrice(){
        CryptoData result = daService.getMaxPrice(generateSingleTypeTestData());

        assertCryptoDataNotNull(result);
        assertEquals(10000F, result.getPrice());
    }

    @Test
    void getMinPrice_whenDataAvailable_selectMinPrice(){
        CryptoData result = daService.getMinPrice(generateSingleTypeTestData());

        assertCryptoDataNotNull(result);
        assertEquals(10F, result.getPrice());
    }

    @Test
    void getOldestRecord_whenDataAvailable_selectOldestRecord(){
        CryptoData result = daService.getOldest(generateSingleTypeTestData());

        assertCryptoDataNotNull(result);
        assertEquals(10F, result.getPrice());
    }

    @Test
    void getLatestRecord_whenDataAvailable_selectLatestRecord(){
        CryptoData result = daService.getLatest(generateSingleTypeTestData());

        assertCryptoDataNotNull(result);
        assertEquals(100F, result.getPrice());
    }

    private Stream<CryptoData> generateSingleTypeTestData(){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(101);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(102);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTimeInMillis(103);
        Calendar calendar4 = Calendar.getInstance();
        calendar4.setTimeInMillis(104);
        return List.of(
                CryptoData.builder().price(10F).symbol("BTC").timestamp(calendar1).build(),
                CryptoData.builder().price(1000F).symbol("BTC").timestamp(calendar2).build(),
                CryptoData.builder().price(10000F).symbol("BTC").timestamp(calendar3).build(),
                CryptoData.builder().price(100F).symbol("BTC").timestamp(calendar4).build()
                ).stream();
    }

    private void assertCryptoDataNotNull(CryptoData data){
        assertNotNull(data);
        assertNotNull(data.getPrice());
        assertNotNull(data.getSymbol());
        assertNotNull(data.getTimestamp());
    }
}
