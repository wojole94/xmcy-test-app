package com.xmcy.test.recommendation.service.service;

import com.xmcy.test.recommendation.service.dto.CryptoProcessingResult;

import java.util.Optional;


public interface RecommendationService {

    Optional<CryptoProcessingResult> getOrderedCryptosByNormalizedRange();
}
