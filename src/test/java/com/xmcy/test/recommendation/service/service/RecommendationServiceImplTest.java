package com.xmcy.test.recommendation.service.service;

import com.xmcy.test.recommendation.service.dto.CryptoProcessingResult;
import com.xmcy.test.recommendation.service.model.CryptoData;
import com.xmcy.test.recommendation.service.model.CryptoNormalizedPricesData;
import com.xmcy.test.recommendation.service.model.StatisticsEnum;
import com.xmcy.test.recommendation.service.readers.CryptoInputDataReader;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {RecommendationServiceImpl.class})
class RecommendationServiceImplTest {
    @MockBean
    DataAnalysisService dataAnalysisService;

    @MockBean
    CryptoInputDataReader cryptoInputDataReader;

    @Autowired
    RecommendationService service;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        when(cryptoInputDataReader.openData(anyString())).thenReturn(generateBTCTypeTestData());

    }

    @ParameterizedTest
    @EnumSource(value = StatisticsEnum.class)
    void getBasicStatistics_whenTypeSelected_thenProperActionPerformed(StatisticsEnum type){
        Stream<CryptoData> inputStream = generateBTCTypeTestData();
        when(cryptoInputDataReader.openData(anyString())).thenReturn(inputStream);

        service.getBasicStatistics(type, "BTC");

        assertCorrectActionRan(type, inputStream);
    }

    @Test
    void getOrderedCryptosByNormalizedRange_whenRun_properActionsInvoked(){
        List<Stream<CryptoData>> inputStream = List.of(generateBTCTypeTestData());
        when(cryptoInputDataReader.openWholeDataInPath()).thenReturn(inputStream);

        service.getOrderedCryptosByNormalizedRange();

        verify(cryptoInputDataReader).openWholeDataInPath();
        verify(dataAnalysisService).getOrderedNormalizedRange(inputStream);
    }

    @Test
    void getHighestNormalizedRangeCryptoForDay_whenRun_properActionsInvoked(){
        List<Stream<CryptoData>> inputStream = List.of(generateBTCTypeTestData());
        LocalDate currentDate = LocalDate.now();
        LocalDateTime from = LocalDateTime.of(currentDate, LocalTime.ofSecondOfDay(0));
        LocalDateTime to = LocalDateTime.of(currentDate.plusDays(1), LocalTime.ofSecondOfDay(0));
        when(cryptoInputDataReader.openWholeDataInPath()).thenReturn(inputStream);
        when(dataAnalysisService.getMaxNormalizedRangeForTimeRange(from, to, inputStream))
                .thenReturn(CryptoNormalizedPricesData.builder().build());

        service.getHighestNormalizedRangeCryptoForDay(currentDate);

        verify(cryptoInputDataReader).openWholeDataInPath();
        verify(dataAnalysisService).getMaxNormalizedRangeForTimeRange(
                LocalDateTime.of(LocalDate.now(), LocalTime.ofSecondOfDay(0)),
                LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.ofSecondOfDay(0)),
                inputStream);
    }

    private void assertCorrectActionRan(StatisticsEnum type, Stream<CryptoData> inputStream){
        switch (type) {
            case MAX -> verify(dataAnalysisService).getMaxPriceByCurrency(LocalDateTime.MIN, LocalDateTime.MAX, inputStream);
            case MIN -> verify(dataAnalysisService).getMinPriceByCurrency(LocalDateTime.MIN, LocalDateTime.MAX, inputStream);
            case OLDEST -> verify(dataAnalysisService).getOldestByCurrency(LocalDateTime.MIN, LocalDateTime.MAX, inputStream);
            case LATEST -> verify(dataAnalysisService).getLatestByCurrency(LocalDateTime.MIN, LocalDateTime.MAX, inputStream);
        }
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
}
