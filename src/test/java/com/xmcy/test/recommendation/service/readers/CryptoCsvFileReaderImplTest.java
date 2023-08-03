package com.xmcy.test.recommendation.service.readers;

import com.xmcy.test.recommendation.service.exception.ExpectedDataTypeNotFoundException;
import com.xmcy.test.recommendation.service.model.CryptoData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CryptoCsvFileReaderImplTest {

    private CryptoInputDataReader reader;

    @BeforeEach
    void init(){
        reader = new CryptoCsvFileReader("src/test/resources/data/");
    }

    @Test
    void openData_whenFileExists_thenReturnWholeData(){
        final String currencyName = "BTC";
        Stream<CryptoData> resultStream = reader.openData(currencyName);
        assertStreamDataFetched(resultStream);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n", "abcd"})
    void openData_whenFileNotExists_thenThrowException(String currencyName) {
        assertThrows(ExpectedDataTypeNotFoundException.class, () -> reader.openData(currencyName));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n", "abcd", "BTC"})
    void openData_whenObjectWrongCreated_thenThrowException(String currencyName){
        CryptoInputDataReader customReader = new CryptoCsvFileReader("/wrongPath");
        assertThrows(ExpectedDataTypeNotFoundException.class, () -> customReader.openData(currencyName));
    }

    @Test
    void openData_whenFileIsMalformed_thenNoDataFetched(){
        final String currencyName = "MALFORMED";
        Stream<CryptoData> resultStream = reader.openData(currencyName);
        assertNoDataFetched(resultStream);
    }

    @Test
    void openWholeDataInPath_whenFilesExists_thenReturnWholeData(){
        List<Stream<CryptoData>> resultStreams = reader.openWholeDataInPath();
        assertWholeDataFetched(resultStreams);
    }

    @Test
    void openWholeDataInPath_whenObjectWrongCreated_thenThrowException(){
        CryptoInputDataReader customReader = new CryptoCsvFileReader("/wrongPath");
        assertThrows(ExpectedDataTypeNotFoundException.class, customReader::openWholeDataInPath);
    }

    private void assertWholeDataFetched(List<Stream<CryptoData>> resultStreams){
        assertEquals(6, resultStreams.size());
        resultStreams.forEach(stream -> {
            assertNotNull(stream);
            assertTableWithoutNulls(stream.collect(Collectors.toList()));
        });
    }

    private void assertStreamDataFetched(Stream<CryptoData> resultStream) {
        assertBatchDataFetched(resultStream.collect(Collectors.toList()));
    }


    private void assertBatchDataFetched(List<CryptoData> resultList){
        assertNotNull(resultList);
        assertEquals(100, resultList.size());
        assertTableWithoutNulls(resultList);
    }

    private void assertNoDataFetched(Stream<CryptoData> resultStream){
        List<CryptoData> results = resultStream.collect(Collectors.toList());
        assertNotNull(results);
        assertEquals(0, results.size());
    }

    private void assertTableWithoutNulls(List<CryptoData> resultList){
        resultList.forEach(data -> {
            assertNotNull(data.getTimestamp());
            assertNotNull(data.getPrice());
            assertNotNull(data.getSymbol());
        });
    }
}
