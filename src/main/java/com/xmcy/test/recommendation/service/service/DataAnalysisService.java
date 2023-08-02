package com.xmcy.test.recommendation.service.service;

import com.xmcy.test.recommendation.service.model.CryptoData;
import com.xmcy.test.recommendation.service.model.CryptoNormalizedPricesData;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

public interface DataAnalysisService {
    CryptoData getMaxPrice(Stream<CryptoData> dataStream);

    CryptoData getMinPrice(Stream<CryptoData> dataStream);

    CryptoData getLatest(Stream<CryptoData> dataStream);

    CryptoData getOldest(Stream<CryptoData> dataStream);

    List<CryptoNormalizedPricesData> getOrderedNormalizedRange(Stream<CryptoData>... dataStream);

    CryptoNormalizedPricesData getMaxNormalizedRangeForParticularDay(LocalDateTime expectedDate, Stream<CryptoData>... dataStream);
}
