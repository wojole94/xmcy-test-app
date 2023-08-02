package com.xmcy.test.recommendation.service.service;

import com.xmcy.test.recommendation.service.model.CryptoData;
import com.xmcy.test.recommendation.service.model.CryptoNormalizedPricesData;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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

    public List<CryptoNormalizedPricesData> getOrderedNormalizedRange(Stream<CryptoData>... dataStream){
        List<CryptoNormalizedPricesData> analyzedData =
                concatStreams(dataStream)
                .collect(Collectors.groupingBy(CryptoData::getSymbol))
                .entrySet()
                .stream()
                .map(entry -> new CryptoNormalizedPricesData(
                        entry.getKey(),
                        calculatePriceNormalizedRange(entry.getValue().stream())
                        )
                )
                .sorted(Comparator.comparing(CryptoNormalizedPricesData::getNormalizedPrice))
                .collect(Collectors.toList());

        Collections.reverse(analyzedData);

        return analyzedData;
    }

    private Stream<CryptoData> concatStreams(Stream<CryptoData>[] dataStream) {
        return Stream.of(dataStream)
                .reduce(Stream::concat)
                .orElseGet(Stream::empty);
    }

    private Double calculatePriceNormalizedRange(Stream<CryptoData> dataStream){
        var stats = dataStream.collect(Collectors.summarizingDouble(data -> data.getPrice()));
        double minPrice = stats.getMin();
        double maxPrice = stats.getMax();

        return (maxPrice - minPrice) / minPrice;
    }
}
