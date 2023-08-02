package com.xmcy.test.recommendation.service.controller;

import com.xmcy.test.recommendation.service.dto.CryptoProcessingResult;
import com.xmcy.test.recommendation.service.service.RecommendationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RecomendationServiceControllerImpl implements RecommendationServiceController {

    private final RecommendationService service;

    @GetMapping(value = "/orderedCryptosByNormalizedRange", produces = "application/json")
    @Override
    public ResponseEntity<CryptoProcessingResult> getOrderedCryptosByNormalizedRange() {
        return ResponseEntity.of(service.getOrderedCryptosByNormalizedRange());
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
