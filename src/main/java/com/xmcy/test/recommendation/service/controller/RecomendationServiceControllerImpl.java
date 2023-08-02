package com.xmcy.test.recommendation.service.controller;

import com.xmcy.test.recommendation.service.dto.CryptoProcessingResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecomendationServiceControllerImpl implements RecommendationServiceController {

    @Override
    public ResponseEntity<CryptoProcessingResult> getOrderedCryptosByNormalizedRange() {
        return null;
    }

    @Override
    public ResponseEntity<CryptoProcessingResult> getCryptoExtremeValues() {
        return null;
    }

    @Override
    public ResponseEntity<CryptoProcessingResult> getHighestNormalizedRangeForDay() {
        return null;
    }
}
