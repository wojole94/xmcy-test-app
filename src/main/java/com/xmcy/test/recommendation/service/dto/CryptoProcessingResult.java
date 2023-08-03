package com.xmcy.test.recommendation.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xmcy.test.recommendation.service.model.CryptoData;
import com.xmcy.test.recommendation.service.model.CryptoNormalizedPricesData;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CryptoProcessingResult {
    List<CryptoData> searchResults;
    List<CryptoNormalizedPricesData> analysisResults;
    List<String> errors;
}
