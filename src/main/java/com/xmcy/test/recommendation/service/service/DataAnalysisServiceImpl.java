package com.xmcy.test.recommendation.service.service;

import com.xmcy.test.recommendation.service.model.CryptoData;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Stream;

@Service
public class DataAnalysisServiceImpl implements DataAnalysisService{
    public CryptoData getMaxPrice(Stream<CryptoData> dataStream){
        return dataStream.max(Comparator.comparing(CryptoData::getPrice)).orElseThrow();
    }

    public CryptoData getMinPrice(Stream<CryptoData> dataStream){
        return dataStream.min(Comparator.comparing(CryptoData::getPrice)).orElseThrow();
    }

    public CryptoData getLatest(Stream<CryptoData> dataStream){
        return dataStream.max(Comparator.comparing(CryptoData::getTimestamp)).orElseThrow();
    }

    public CryptoData getOldest(Stream<CryptoData> dataStream){
        return dataStream.min(Comparator.comparing(CryptoData::getTimestamp)).orElseThrow();
    }
}
