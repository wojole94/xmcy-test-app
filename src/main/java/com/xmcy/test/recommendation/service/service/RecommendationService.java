package com.xmcy.test.recommendation.service.service;

import com.xmcy.test.recommendation.service.dto.CryptoProcessingResult;
import com.xmcy.test.recommendation.service.model.StatisticsEnum;

import java.time.LocalDate;
import java.util.Optional;


public interface RecommendationService {

    Optional<CryptoProcessingResult> getOrderedCryptosByNormalizedRange();
    Optional<CryptoProcessingResult> getBasicStatistics(StatisticsEnum type, String cryptoSymbol);
    Optional<CryptoProcessingResult> getHighestNormalizedRangeCryptoForDay(LocalDate date);

}
