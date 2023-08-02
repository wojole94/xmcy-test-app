package com.xmcy.test.recommendation.service.controller;

import com.xmcy.test.recommendation.service.dto.CryptoProcessingResult;
import org.springframework.http.ResponseEntity;

public interface RecommendationServiceController {

    /**
     *     Exposes an endpoint that will return a descending sorted list of all the cryptos,
     *     comparing the normalized range (i.e. (max-min)/min)
     */
    public ResponseEntity<CryptoProcessingResult> getOrderedCryptosByNormalizedRange();

    /**
     *     Exposes an endpoint that will return the oldest/newest/min/max values for a requested
     *     crypto
     */
    public ResponseEntity<CryptoProcessingResult> getCryptoExtremeValues();

    /**
     *     Exposes an endpoint that will return the crypto with the highest normalized range for a
     *     specific day
     */
    public ResponseEntity<CryptoProcessingResult> getHighestNormalizedRangeForDay();

}
