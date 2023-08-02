package com.xmcy.test.recommendation.service.controller;

import com.xmcy.test.recommendation.service.model.CryptoResult;
import org.springframework.http.ResponseEntity;

public interface RecommendationServiceController {

    /**
     *     Exposes an endpoint that will return a descending sorted list of all the cryptos,
     *     comparing the normalized range (i.e. (max-min)/min)
     */
    public ResponseEntity<CryptoResult> getOrderedCryptosByNormalizedRange();

    /**
     *     Exposes an endpoint that will return the oldest/newest/min/max values for a requested
     *     crypto
     */
    public ResponseEntity<CryptoResult> getCryptoExtremeValues();

    /**
     *     Exposes an endpoint that will return the crypto with the highest normalized range for a
     *     specific day
     */
    public ResponseEntity<CryptoResult> getHighestNormalizedRangeForDay();

}
