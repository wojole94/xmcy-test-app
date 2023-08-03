package com.xmcy.test.recommendation.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CryptoNormalizedPricesData {
    private String symbol;
    private Double normalizedPrice;
}
