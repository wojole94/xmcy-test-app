package com.xmcy.test.recommendation.service.readers;

import com.xmcy.test.recommendation.service.model.CryptoData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CryptoCsvFileReaderTest {

    private CryptoInputDataReader reader;

    @BeforeEach
    void init(){
        reader = new CryptoCsvFileReader("data/");
    }

    @Test
    void readDataAsWholeBatch_whenFileExists_thenReturnWholeData(){
        final String currencyName = "BTC";

        List<CryptoData> resultList = reader.readDataAsWholeBatch(currencyName);

        assertBatchDataFetched(resultList);
    }


    private void assertBatchDataFetched(List<CryptoData> resultList){
        assertEquals(100, resultList.size());
    }
}
