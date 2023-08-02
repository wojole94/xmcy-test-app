package com.xmcy.test.recommendation.service.service;

import com.xmcy.test.recommendation.service.model.CryptoData;

import com.xmcy.test.recommendation.service.model.CryptoNormalizedPricesData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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
        assertEquals(11234D, result.getPrice());
    }

    @Test
    void getMinPrice_whenDataAvailable_selectMinPrice() {
        CryptoData result = daService.getMinPrice(generateBTCTypeTestData());

        assertCryptoDataNotNull(result);
        assertEquals(11D, result.getPrice());
    }

    @Test
    void getOldestRecord_whenDataAvailable_selectOldestRecord() {
        CryptoData result = daService.getOldest(generateBTCTypeTestData());

        assertCryptoDataNotNull(result);
        assertEquals(11D, result.getPrice());
    }

    @Test
    void getLatestRecord_whenDataAvailable_selectLatestRecord() {
        CryptoData result = daService.getLatest(generateBTCTypeTestData());

        assertCryptoDataNotNull(result);
        assertEquals(112D, result.getPrice());
    }

    @Test
    void getOrderedNormalizedRange_whenDataAvailable_getNormalizedValuesPerCrypto() {
        List<CryptoNormalizedPricesData> result = daService.getOrderedNormalizedRange(generateBTCTypeTestData(), generateDOGETypeTestData(), generateETHTypeTestData());

        result.forEach(this::assertCryptoNormalizedPricesDataNotNull);
    }

    @Test
    void getMaxNormalizedRangeForParticularDay_whenDataAvailable_selectLatestRecord() {
        LocalDateTime expectedDate = LocalDateTime.of(2022,2,3,0,0);

        CryptoNormalizedPricesData result = daService.getMaxNormalizedRangeForParticularDay(expectedDate, generateDOGETypeTestData(), generateBTCTypeTestData(), generateETHTypeTestData());

        assertCryptoNormalizedPricesDataNotNull(result);
    }

    private Stream<CryptoData> generateBTCTypeTestData() {
        LocalDateTime date1 = LocalDateTime.of(2022,2,3,1,0);
        LocalDateTime date2 = LocalDateTime.of(2022,2,3,2,0);
        LocalDateTime date3 = LocalDateTime.of(2022,2,3,3,0);
        LocalDateTime date4 = LocalDateTime.of(2022,2,3,4,0);

        return Stream.of(
                CryptoData.builder().price(11D).symbol("BTC").timestamp(date1).build(),
                CryptoData.builder().price(1123D).symbol("BTC").timestamp(date2).build(),
                CryptoData.builder().price(11234D).symbol("BTC").timestamp(date3).build(),
                CryptoData.builder().price(112D).symbol("BTC").timestamp(date4).build()
        );
    }

    private Stream<CryptoData> generateDOGETypeTestData() {

        LocalDateTime date1 = LocalDateTime.of(2022,2,3,1,0);
        LocalDateTime date2 = LocalDateTime.of(2022,2,3,2,0);
        LocalDateTime date3 = LocalDateTime.of(2022,2,3,3,0);
        LocalDateTime date4 = LocalDateTime.of(2022,2,3,4,0);

        return Stream.of(
                CryptoData.builder().price(21D).symbol("DOGE").timestamp(date1).build(),
                CryptoData.builder().price(2123D).symbol("DOGE").timestamp(date2).build(),
                CryptoData.builder().price(21234D).symbol("DOGE").timestamp(date3).build(),
                CryptoData.builder().price(212D).symbol("DOGE").timestamp(date4).build()
        );
    }

    private Stream<CryptoData> generateETHTypeTestData() {

        LocalDateTime date1 = LocalDateTime.of(2022,2,3,1,0);
        LocalDateTime date2 = LocalDateTime.of(2022,2,3,2,0);
        LocalDateTime date3 = LocalDateTime.of(2022,2,3,3,0);
        LocalDateTime date4 = LocalDateTime.of(2022,2,3,4,0);

        return Stream.of(
                CryptoData.builder().price(31D).symbol("ETH").timestamp(date1).build(),
                CryptoData.builder().price(3123D).symbol("ETH").timestamp(date2).build(),
                CryptoData.builder().price(31234D).symbol("ETH").timestamp(date3).build(),
                CryptoData.builder().price(312D).symbol("ETH").timestamp(date4).build()
        );
    }

    private void assertCryptoDataNotNull(CryptoData data) {
        assertNotNull(data);
        assertNotNull(data.getPrice());
        assertNotNull(data.getSymbol());
        assertNotNull(data.getTimestamp());
    }

    private void assertCryptoNormalizedPricesDataNotNull(CryptoNormalizedPricesData data){
        assertNotNull(data);
        assertNotNull(data.getNormalizedPrice());
        assertNotNull(data.getSymbol());
    }
}
