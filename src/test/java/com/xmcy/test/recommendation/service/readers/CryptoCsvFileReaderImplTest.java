package com.xmcy.test.recommendation.service.readers;

import com.xmcy.test.recommendation.service.model.CryptoData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CryptoCsvFileReaderImplTest {

    private CryptoInputDataReader reader;

    @BeforeEach
    void init(){
        reader = new CryptoCsvFileReader("data/");
    }

    @Test
    void readDataIncrementally_whenFileExists_thenReturnWholeData(){
        final String currencyName = "BTC";

        Stream<CryptoData> resultStream = reader.openData(currencyName);

        assertStreamDataFetched(resultStream);
    }

    //TODO add exception checks

    private void assertStreamDataFetched(Stream<CryptoData> resultStream) {
        assertBatchDataFetched(resultStream.collect(Collectors.toList()));
    }


    private void assertBatchDataFetched(List<CryptoData> resultList){
        assertNotNull(resultList);
        assertEquals(100, resultList.size());
        assertTableWithoutNulls(resultList);
    }

    private void assertTableWithoutNulls(List<CryptoData> resultList){
        resultList.forEach(data -> {
            assertNotNull(data.getTimestamp());
            assertNotNull(data.getPrice());
            assertNotNull(data.getSymbol());
        });
    }
}
