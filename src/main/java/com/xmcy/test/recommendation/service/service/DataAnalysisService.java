package com.xmcy.test.recommendation.service.service;

import com.xmcy.test.recommendation.service.model.CryptoData;
import com.xmcy.test.recommendation.service.model.CryptoNormalizedPricesData;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DataAnalysisService {

    //Pass only streaam for one crypto if for particular one
    public List<CryptoData> getMaxPriceByCurrency(LocalDateTime from, LocalDateTime to, Stream<CryptoData> dataStream) {
        return getMaxPriceByCurrency(from, to, List.of(dataStream));
    }

    public List<CryptoData> getMaxPriceByCurrency(LocalDateTime from, LocalDateTime to, List<Stream<CryptoData>> dataStream) {
        Stream<CryptoData> concatStreams = concatStreams(dataStream);
        Stream<CryptoData> outfilteredData = filterTimeframe(concatStreams, from, to);
        return outfilteredData
                .collect(Collectors.groupingBy(CryptoData::getSymbol))
                .values()
                .stream()
                .map(cryptoData -> cryptoData.stream().max(Comparator.comparing(CryptoData::getPrice)).orElseThrow())
                .toList();

    }

    public List<CryptoData> getMinPriceByCurrency(LocalDateTime from, LocalDateTime to, Stream<CryptoData> dataStream) {
        return getMinPriceByCurrency(from, to, List.of(dataStream));
    }

    public List<CryptoData> getMinPriceByCurrency(LocalDateTime from, LocalDateTime to, List<Stream<CryptoData>> dataStream) {
        Stream<CryptoData> concatStreams = concatStreams(dataStream);
        Stream<CryptoData> outfilteredData = filterTimeframe(concatStreams, from, to);
        return outfilteredData
                .collect(Collectors.groupingBy(CryptoData::getSymbol))
                .values()
                .stream()
                .map(cryptoData -> cryptoData.stream().min(Comparator.comparing(CryptoData::getPrice)).orElseThrow())
                .toList();
    }

    public List<CryptoData> getLatestByCurrency(LocalDateTime from, LocalDateTime to, Stream<CryptoData> dataStream) {
        return getLatestByCurrency(from, to, List.of(dataStream));
    }

    public List<CryptoData> getLatestByCurrency(LocalDateTime from, LocalDateTime to, List<Stream<CryptoData>> dataStream) {
        Stream<CryptoData> concatStreams = concatStreams(dataStream);
        Stream<CryptoData> outfilteredData = filterTimeframe(concatStreams, from, to);
        return outfilteredData
                .collect(Collectors.groupingBy(CryptoData::getSymbol))
                .values()
                .stream()
                .map(cryptoData -> cryptoData.stream().max(Comparator.comparing(CryptoData::getTimestamp)).orElseThrow())
                .toList();
    }

    public List<CryptoData> getOldestByCurrency(LocalDateTime from, LocalDateTime to, Stream<CryptoData> dataStream) {
        return getOldestByCurrency(from, to, List.of(dataStream));
    }

    public List<CryptoData> getOldestByCurrency(LocalDateTime from, LocalDateTime to, List<Stream<CryptoData>> dataStream) {
        Stream<CryptoData> concatStreams = concatStreams(dataStream);
        Stream<CryptoData> outfilteredData = filterTimeframe(concatStreams, from, to);
        return outfilteredData
                .collect(Collectors.groupingBy(CryptoData::getSymbol))
                .values()
                .stream()
                .map(cryptoData -> cryptoData.stream().min(Comparator.comparing(CryptoData::getTimestamp)).orElseThrow())
                .toList();
    }

    public List<CryptoNormalizedPricesData> getOrderedNormalizedRange(Stream<CryptoData> dataStream){
        return getOrderedNormalizedRange(List.of(dataStream));
    }
    public List<CryptoNormalizedPricesData> getOrderedNormalizedRange(List<Stream<CryptoData>> dataStream){
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
    public CryptoNormalizedPricesData getMaxNormalizedRangeForTimeRange(LocalDateTime from, LocalDateTime to, Stream<CryptoData> dataStream){
        return getMaxNormalizedRangeForTimeRange(from, to, List.of(dataStream));
    }
    public CryptoNormalizedPricesData getMaxNormalizedRangeForTimeRange(LocalDateTime from, LocalDateTime to, List<Stream<CryptoData>> dataStream){
        Stream<CryptoData> concatStreams = concatStreams(dataStream);
        Stream<CryptoData> filteredData = filterTimeframe(concatStreams, from, to);
        return getOrderedNormalizedRange(filteredData)
                .stream()
                .findFirst()
                .orElseThrow();
    }

    private Stream<CryptoData> concatStreams(List<Stream<CryptoData>> dataStream) {
        return dataStream.stream()
                .reduce(Stream::concat)
                .orElseGet(Stream::empty);
    }

    private Stream<CryptoData> filterTimeframe(Stream<CryptoData> dataStream, LocalDateTime from, LocalDateTime to) {
        return dataStream.filter(entry -> entry.getTimestamp().isAfter(from)
                && entry.getTimestamp().isBefore(to));
    }

    private Double calculatePriceNormalizedRange(Stream<CryptoData> dataStream) {
        DoubleSummaryStatistics stats = dataStream.collect(Collectors.summarizingDouble(CryptoData::getPrice));
        return (stats.getMax() - stats.getMin()) / stats.getMin();
    }
}
