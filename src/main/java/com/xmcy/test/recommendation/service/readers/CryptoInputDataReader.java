package com.xmcy.test.recommendation.service.readers;

import com.xmcy.test.recommendation.service.model.CryptoData;

import java.util.List;
import java.util.stream.Stream;

public interface CryptoInputDataReader {
    List<CryptoData> readDataAsWholeBatch(String cryptoName);
    Stream<CryptoData> readDataIncrementally(String cryptoName);


}
