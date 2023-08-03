package com.xmcy.test.recommendation.service.controller;

import com.xmcy.test.recommendation.service.dto.CryptoProcessingResult;
import com.xmcy.test.recommendation.service.model.StatisticsEnum;
import com.xmcy.test.recommendation.service.service.RecommendationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("recommendation-service")
@AllArgsConstructor
public class RecommendationServiceControllerImpl implements RecommendationServiceController {
    private final RecommendationService service;

    @Override
    @GetMapping(value = "/orderedCryptosByNormalizedRange", produces = "application/json")
    public ResponseEntity<CryptoProcessingResult> getOrderedCryptosByNormalizedRange() {
        return ResponseEntity.of(service.getOrderedCryptosByNormalizedRange());
    }

    @Override
    @GetMapping(value = "/cryptoExtremeValue/{cryptoSymbol}", produces = "application/json")
    public ResponseEntity<CryptoProcessingResult> getCryptoExtremeValues(@RequestParam StatisticsEnum type,
                                                                         @PathVariable("cryptoSymbol") String cryptoSymbol) {
        return ResponseEntity.of(service.getBasicStatistics(type, cryptoSymbol));
    }

    @Override
    @GetMapping(value = "/highestNormalizedRangeForDay", produces = "application/json")
    public ResponseEntity<CryptoProcessingResult> getHighestNormalizedRangeForDay(@RequestParam LocalDate date) {
        return ResponseEntity.of(service.getHighestNormalizedRangeCryptoForDay(date));
    }
}
