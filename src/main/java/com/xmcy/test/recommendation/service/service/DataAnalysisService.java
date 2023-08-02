package com.xmcy.test.recommendation.service.service;

import com.xmcy.test.recommendation.service.model.CryptoData;

import java.util.stream.Stream;

public interface DataAnalysisService {
    CryptoData getMaxPrice(Stream<CryptoData> dataStream);

    CryptoData getMinPrice(Stream<CryptoData> dataStream);

    CryptoData getLatest(Stream<CryptoData> dataStream);

    CryptoData getOldest(Stream<CryptoData> dataStream);
}
