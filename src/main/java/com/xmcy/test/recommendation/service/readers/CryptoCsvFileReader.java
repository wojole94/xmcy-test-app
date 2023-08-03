package com.xmcy.test.recommendation.service.readers;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.xmcy.test.recommendation.service.model.CryptoData;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public class CryptoCsvFileReader implements CryptoInputDataReader {

    private final String csvRootPath;
    private static final String FILE_NAME_SUFFIX = "_values.csv";

    public List<CryptoData> readDataAsWholeBatch(String cryptoName) {
        FileReader filereader = null;
        try {
            filereader = new FileReader(getFilePath(cryptoName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        CsvToBean<CryptoData> csvReader =
                new CsvToBeanBuilder<CryptoData>(filereader)
                        .withType(CryptoData.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

        return csvReader.parse();
    }

    public Stream<CryptoData> openData(String cryptoName) {
        return openData(getFilePath(cryptoName));
    }

    public Stream<CryptoData> openData(File file){
        FileReader filereader = null;
        try {
            filereader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        CsvToBean<CryptoData> csvReader =
                new CsvToBeanBuilder<CryptoData>(filereader)
                        .withType(CryptoData.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

        return StreamSupport.stream(csvReader.spliterator(), false);
    }
    public List<Stream<CryptoData>> openWholeDataInPath() {
        File[] dir = new File(csvRootPath).listFiles();
        return Arrays.stream(dir)
                .filter(file -> file.isFile() && file.getName().contains(FILE_NAME_SUFFIX))
                .map(this::openData)
                .toList();
    }

    private File getFilePath(String cryptoName) {
        return new File(csvRootPath + cryptoName + FILE_NAME_SUFFIX);
    }
}
