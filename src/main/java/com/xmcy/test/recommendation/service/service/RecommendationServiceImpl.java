package com.xmcy.test.recommendation.service.service;

import com.xmcy.test.recommendation.service.dto.CryptoProcessingResult;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecommendationServiceImpl implements RecommendationService{
    @Override
    public Optional<CryptoProcessingResult> getOrderedCryptosByNormalizedRange() {
        return null;
    }
}
