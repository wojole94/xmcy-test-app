package com.xmcy.test.recommendation.service.readers;

import com.xmcy.test.recommendation.service.model.CryptoData;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

public interface CryptoInputDataReader {
    List<CryptoData> readDataAsWholeBatch(String cryptoName);
    Stream<CryptoData> openData(String cryptoName);
    Stream<CryptoData> openData(File fileName);
    List<Stream<CryptoData>> openWholeDataInPath();


}
