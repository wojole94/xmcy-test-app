package com.xmcy.test.recommendation.service.service;

import com.xmcy.test.recommendation.service.model.CryptoData;
import com.xmcy.test.recommendation.service.model.CryptoNormalizedPricesData;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
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

    public CryptoNormalizedPricesData getMaxNormalizedRangeForParticularDay(LocalDateTime expectedDate, Stream<CryptoData>... dataStream){
        LocalDateTime from = LocalDateTime.of(
                expectedDate.getYear(),
                expectedDate.getMonth(),
                expectedDate.getDayOfMonth(),
                0,
                0,
                0);

        LocalDateTime to = LocalDateTime.of(
                expectedDate.getYear(),
                expectedDate.getMonth(),
                expectedDate.getDayOfMonth(),
                0,
                0,
                0)
                .plusDays(1);

        Stream<CryptoData> outfilteredData = concatStreams(dataStream)
                .filter(entry -> entry.getTimestamp().isAfter(from)
                        && entry.getTimestamp().isBefore(to));
        return getOrderedNormalizedRange(outfilteredData)
                .stream()
                .max(Comparator.comparing(CryptoNormalizedPricesData::getNormalizedPrice))
                .orElseThrow();
    }



    private Stream<CryptoData> concatStreams(Stream<CryptoData>[] dataStream) {
        return Stream.of(dataStream)
                .reduce(Stream::concat)
                .orElseGet(Stream::empty);
    }

    private Double calculatePriceNormalizedRange(Stream<CryptoData> dataStream){
        DoubleSummaryStatistics stats = dataStream.collect(Collectors.summarizingDouble(CryptoData::getPrice));
        return (stats.getMax() - stats.getMin()) / stats.getMin();
    }
}
