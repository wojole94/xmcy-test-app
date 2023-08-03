package com.xmcy.test.recommendation.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class CryptoNormalizedPricesData {
    private String symbol;
    private Double normalizedPrice;
}
