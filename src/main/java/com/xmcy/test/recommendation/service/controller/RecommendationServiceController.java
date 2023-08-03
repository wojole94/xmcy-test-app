package com.xmcy.test.recommendation.service.controller;

import com.xmcy.test.recommendation.service.dto.CryptoProcessingResult;
import com.xmcy.test.recommendation.service.model.StatisticsEnum;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;

public interface RecommendationServiceController {

    /**
     *     Exposes an endpoint that will return a descending sorted list of all the cryptos,
     *     comparing the normalized range (i.e. (max-min)/min)
     */
    ResponseEntity<CryptoProcessingResult> getOrderedCryptosByNormalizedRange();

    /**
     *     Exposes an endpoint that will return the oldest/newest/min/max values for a requested
     *     crypto
     *     @param type allows values: MAX, MIN, OLDEST, LATEST
     *     @param cryptoSymbol cryptocurrency symbol
     */
    ResponseEntity<CryptoProcessingResult> getCryptoExtremeValues(StatisticsEnum type, String cryptoSymbol);

    /**
     *     Exposes an endpoint that will return the crypto with the highest normalized range for a
     *     specific day
     *     @param date desiredDate in format dd-MM-yyyy
     */
    ResponseEntity<CryptoProcessingResult> getHighestNormalizedRangeForDay(LocalDate date);

}
