package com.xmcy.test.recommendation.service.service;

import com.xmcy.test.recommendation.service.model.CryptoData;

import com.xmcy.test.recommendation.service.model.CryptoNormalizedPricesData;
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
    void init() {
        daService = new DataAnalysisServiceImpl();
    }

    @Test
    void getMaxPrice_whenDataAvailable_selectMaxPrice() {
        CryptoData result = daService.getMaxPrice(generateBTCTypeTestData());

        assertCryptoDataNotNull(result);
        assertEquals(10000D, result.getPrice());
    }

    @Test
    void getMinPrice_whenDataAvailable_selectMinPrice() {
        CryptoData result = daService.getMinPrice(generateBTCTypeTestData());

        assertCryptoDataNotNull(result);
        assertEquals(10D, result.getPrice());
    }

    @Test
    void getOldestRecord_whenDataAvailable_selectOldestRecord() {
        CryptoData result = daService.getOldest(generateBTCTypeTestData());

        assertCryptoDataNotNull(result);
        assertEquals(10D, result.getPrice());
    }

    @Test
    void getLatestRecord_whenDataAvailable_selectLatestRecord() {
        CryptoData result = daService.getLatest(generateBTCTypeTestData());

        assertCryptoDataNotNull(result);
        assertEquals(100D, result.getPrice());
    }

    @Test
    void getOrderedNormalizedRange_whenDataAvailable_selectLatestRecord() {
        List<CryptoNormalizedPricesData> result = daService.getOrderedNormalizedRange(generateBTCTypeTestData(), generateDOGETypeTestData(), generateETHTypeTestData());
        assertNotNull(result);
    }

    private Stream<CryptoData> generateBTCTypeTestData() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(101);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(102);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTimeInMillis(103);
        Calendar calendar4 = Calendar.getInstance();
        calendar4.setTimeInMillis(104);
        return List.of(
                CryptoData.builder().price(11D).symbol("BTC").timestamp(calendar1).build(),
                CryptoData.builder().price(1123D).symbol("BTC").timestamp(calendar2).build(),
                CryptoData.builder().price(11234D).symbol("BTC").timestamp(calendar3).build(),
                CryptoData.builder().price(112D).symbol("BTC").timestamp(calendar4).build()
        ).stream();
    }

    private Stream<CryptoData> generateDOGETypeTestData() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(101);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(102);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTimeInMillis(103);
        Calendar calendar4 = Calendar.getInstance();
        calendar4.setTimeInMillis(104);
        return List.of(
                CryptoData.builder().price(21D).symbol("DOGE").timestamp(calendar1).build(),
                CryptoData.builder().price(2123D).symbol("DOGE").timestamp(calendar2).build(),
                CryptoData.builder().price(21234D).symbol("DOGE").timestamp(calendar3).build(),
                CryptoData.builder().price(212D).symbol("DOGE").timestamp(calendar4).build()
        ).stream();
    }

    private Stream<CryptoData> generateETHTypeTestData() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(101);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(102);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTimeInMillis(103);
        Calendar calendar4 = Calendar.getInstance();
        calendar4.setTimeInMillis(104);
        return List.of(
                CryptoData.builder().price(31D).symbol("ETH").timestamp(calendar1).build(),
                CryptoData.builder().price(3123D).symbol("ETH").timestamp(calendar2).build(),
                CryptoData.builder().price(31234D).symbol("ETH").timestamp(calendar3).build(),
                CryptoData.builder().price(312D).symbol("ETH").timestamp(calendar4).build()
        ).stream();
    }

    private void assertCryptoDataNotNull(CryptoData data) {
        assertNotNull(data);
        assertNotNull(data.getPrice());
        assertNotNull(data.getSymbol());
        assertNotNull(data.getTimestamp());
    }
}
