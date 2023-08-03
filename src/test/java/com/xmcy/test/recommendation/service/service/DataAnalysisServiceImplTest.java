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
class DataAnalysisServiceImplTest {

    DataAnalysisService daService;

    @BeforeEach
    void init() {
        daService = new DataAnalysisService();
    }

    @Test
    void getMaxPrice_whenDataAvailable_selectMaxPrice() {
        List<CryptoData> result = daService.getMaxPriceByCurrency(LocalDateTime.MIN, LocalDateTime.MAX, generateBTCTypeTestData());

        assertCryptoDataNotNull(result.get(0));
        assertEquals(11234D, result.get(0).getPrice());
    }

    @Test
    void getMaxPrice_whenMultidataAvailable_selectMaxPrice() {
        List<CryptoData> result = daService.getMaxPriceByCurrency(LocalDateTime.MIN, LocalDateTime.MAX,
                List.of(generateBTCTypeTestData(), generateDOGETypeTestData()));

        assertMultiDataMaxResult(result);
    }

    @Test
    void getMinPrice_whenDataAvailable_selectMinPrice() {
        List<CryptoData> result = daService.getMinPriceByCurrency(LocalDateTime.MIN, LocalDateTime.MAX, generateBTCTypeTestData());

        assertCryptoDataNotNull(result.get(0));
        assertEquals(11D, result.get(0).getPrice());
    }

    @Test
    void getMinPrice_whenMultiDataAvailable_selectMinPrice() {
        List<CryptoData> result = daService.getMinPriceByCurrency(LocalDateTime.MIN, LocalDateTime.MAX,
                List.of(generateBTCTypeTestData(), generateDOGETypeTestData()));

        assertMultiDataMinResult(result);
    }

    @Test
    void getOldestRecord_whenDataAvailable_selectOldestRecord() {
        List<CryptoData> result = daService.getOldestByCurrency(LocalDateTime.MIN, LocalDateTime.MAX, generateBTCTypeTestData());

        assertCryptoDataNotNull(result.get(0));
        assertEquals(11D, result.get(0).getPrice());
    }

    @Test
    void getOldestRecord_whenMultiDataAvailable_selectOldestRecord() {
        List<CryptoData> result = daService.getOldestByCurrency(LocalDateTime.MIN, LocalDateTime.MAX,
                List.of(generateBTCTypeTestData(), generateDOGETypeTestData()));

        assertMultiDataOldestResult(result);
    }

    @Test
    void getLatestRecord_whenDataAvailable_selectLatestRecord() {
        List<CryptoData> result = daService.getLatestByCurrency(LocalDateTime.MIN, LocalDateTime.MAX, generateBTCTypeTestData());

        assertCryptoDataNotNull(result.get(0));
        assertEquals(112D, result.get(0).getPrice());
    }

    @Test
    void getOldestRecord_whenMultiDataAvailable_selectLatestRecord() {
        List<CryptoData> result = daService.getLatestByCurrency(LocalDateTime.MIN, LocalDateTime.MAX,
                List.of(generateBTCTypeTestData(), generateDOGETypeTestData()));

        assertMultiDataLatestResult(result);
    }

    @Test
    void getOrderedNormalizedRange_whenDataAvailable_getNormalizedValuesPerCrypto() {
        List<CryptoNormalizedPricesData> result = daService.getOrderedNormalizedRange(generateBTCTypeTestData());

        result.forEach(this::assertCryptoNormalizedPricesDataNotNull);
        assertEquals(1020.2727272727273, result.get(0).getNormalizedPrice());
    }

    @Test
    void getMaxNormalizedRangeForParticularDay_whenDataAvailable_selectMaxNormalizedRange() {
        LocalDateTime fromDate = LocalDateTime.of(2022,2,3,0,0);
        LocalDateTime toDate = LocalDateTime.of(2022,2,3,0,0).plusDays(1);

        CryptoNormalizedPricesData result = daService.getMaxNormalizedRangeForTimeRange(fromDate, toDate, generateBTCTypeTestData());

        assertCryptoNormalizedPricesDataNotNull(result);
        assertEquals("BTC", result.getSymbol());
        assertEquals(1020.2727272727273, result.getNormalizedPrice());
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

    private List<Stream<CryptoData>> generateListOfCurrenciesData(){
        return List.of(generateETHTypeTestData(), generateDOGETypeTestData(), generateBTCTypeTestData());
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

    private void assertMultiDataMaxResult(List<CryptoData> result){
        CryptoData btcResult = result.stream().filter(cryptoData -> cryptoData.getSymbol().equals("BTC")).findFirst().get();
        CryptoData dogeResult = result.stream().filter(cryptoData -> cryptoData.getSymbol().equals("DOGE")).findFirst().get();
        assertCryptoDataNotNull(btcResult);
        assertCryptoDataNotNull(dogeResult);
        assertEquals(11234D, btcResult.getPrice());
        assertEquals(21234D, dogeResult.getPrice());
    }

    private void assertMultiDataMinResult(List<CryptoData> result){
        CryptoData btcResult = result.stream().filter(cryptoData -> cryptoData.getSymbol().equals("BTC")).findFirst().get();
        CryptoData dogeResult = result.stream().filter(cryptoData -> cryptoData.getSymbol().equals("DOGE")).findFirst().get();
        assertCryptoDataNotNull(btcResult);
        assertCryptoDataNotNull(dogeResult);
        assertEquals(11D, btcResult.getPrice());
        assertEquals(21D, dogeResult.getPrice());
    }

    private void assertMultiDataLatestResult(List<CryptoData> result){
        CryptoData btcResult = result.stream().filter(cryptoData -> cryptoData.getSymbol().equals("BTC")).findFirst().get();
        CryptoData dogeResult = result.stream().filter(cryptoData -> cryptoData.getSymbol().equals("DOGE")).findFirst().get();
        assertCryptoDataNotNull(btcResult);
        assertCryptoDataNotNull(dogeResult);
        assertEquals(112D, btcResult.getPrice());
        assertEquals(212D, dogeResult.getPrice());
    }

    private void assertMultiDataOldestResult(List<CryptoData> result){
        CryptoData btcResult = result.stream().filter(cryptoData -> cryptoData.getSymbol().equals("BTC")).findFirst().get();
        CryptoData dogeResult = result.stream().filter(cryptoData -> cryptoData.getSymbol().equals("DOGE")).findFirst().get();
        assertCryptoDataNotNull(btcResult);
        assertCryptoDataNotNull(dogeResult);
        assertEquals(11D, btcResult.getPrice());
        assertEquals(21D, dogeResult.getPrice());
    }
}
