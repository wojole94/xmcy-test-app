package com.xmcy.test.recommendation.service.controller;

import com.xmcy.test.recommendation.service.model.CryptoResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecomendationServiceControllerImpl implements RecommendationServiceController {

    @Override
    public ResponseEntity<CryptoResult> getOrderedCryptosByNormalizedRange() {
        return null;
    }

    @Override
    public ResponseEntity<CryptoResult> getCryptoExtremeValues() {
        return null;
    }

    @Override
    public ResponseEntity<CryptoResult> getHighestNormalizedRangeForDay() {
        return null;
    }
}
