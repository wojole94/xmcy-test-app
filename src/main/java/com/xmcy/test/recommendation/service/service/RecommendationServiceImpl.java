package com.xmcy.test.recommendation.service.service;

import com.xmcy.test.recommendation.service.dto.CryptoProcessingResult;
import com.xmcy.test.recommendation.service.model.CryptoData;
import com.xmcy.test.recommendation.service.model.CryptoNormalizedPricesData;
import com.xmcy.test.recommendation.service.model.StatisticsEnum;
import com.xmcy.test.recommendation.service.readers.CryptoInputDataReader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {
    @Autowired DataAnalysisService dataAnalysisService;
    @Autowired CryptoInputDataReader inputDataReader;
    @Override
    public Optional<CryptoProcessingResult> getBasicStatistics(StatisticsEnum type, String cryptoSymbol) {
        Stream<CryptoData> dataStream = inputDataReader.openData(cryptoSymbol);
        final List<CryptoData> analysisResult = switch (type) {
            case MAX -> dataAnalysisService.getMaxPriceByCurrency(LocalDateTime.MIN, LocalDateTime.MAX, dataStream);
            case MIN -> dataAnalysisService.getMinPriceByCurrency(LocalDateTime.MIN, LocalDateTime.MAX, dataStream);
            case LATEST -> dataAnalysisService.getLatestByCurrency(LocalDateTime.MIN, LocalDateTime.MAX, dataStream);
            case OLDEST -> dataAnalysisService.getOldestByCurrency(LocalDateTime.MIN, LocalDateTime.MAX, dataStream);
        };

        return Optional.of(CryptoProcessingResult.builder().searchResults(analysisResult).build());
    }
    @Override
    public Optional<CryptoProcessingResult> getOrderedCryptosByNormalizedRange() {
        List<Stream<CryptoData>> dataStreams = inputDataReader.openWholeDataInPath();
        final List<CryptoNormalizedPricesData> results = dataAnalysisService.getOrderedNormalizedRange(dataStreams);

        return Optional.of(CryptoProcessingResult.builder().analysisResults(results).build());
    }
    @Override
    public Optional<CryptoProcessingResult> getHighestNormalizedRangeCryptoForDay(LocalDate date) {
        List<Stream<CryptoData>> dataStreams = inputDataReader.openWholeDataInPath();
        LocalTime zeroTime = LocalTime.ofSecondOfDay(0);
        final CryptoNormalizedPricesData result = dataAnalysisService.getMaxNormalizedRangeForTimeRange(
                LocalDateTime.of(date, zeroTime),
                LocalDateTime.of(date.plusDays(1), zeroTime),
                dataStreams);

        return Optional.of(CryptoProcessingResult.builder().analysisResults(List.of(result)).build());
    }
}
