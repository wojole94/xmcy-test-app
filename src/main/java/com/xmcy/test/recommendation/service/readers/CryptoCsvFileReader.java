package com.xmcy.test.recommendation.service.readers;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.xmcy.test.recommendation.service.exception.ExpectedDataTypeNotFoundException;
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
    private static final String EXCEPTION_MESSAGE = "Cannot find data source!";

    /**
     * Reads all the prices from particular csv file as stream for further processing
     *
     */
    public Stream<CryptoData> openData(String cryptoName) {
        return openData(getFile(cryptoName));
    }

    /**
     * Reads all the prices from particular csv file as stream for further processing
     *
     */
    public Stream<CryptoData> openData(File file){
        FileReader filereader = getFileReader(file);
        CsvToBean<CryptoData> csvReader =
                new CsvToBeanBuilder<CryptoData>(filereader)
                        .withType(CryptoData.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

        return StreamSupport.stream(csvReader.spliterator(), false);
    }

    /**
     * Reads all the prices from all the csv files as streams for further processing
     *
     */
    public List<Stream<CryptoData>> openWholeDataInPath() {
        File[] dir = getAllFilesInDirectory();
        return Arrays.stream(dir)
                .filter(file -> file.isFile() && file.getName().contains(FILE_NAME_SUFFIX))
                .map(this::openData)
                .toList();
    }

    private File[] getAllFilesInDirectory() {
        File[] dir = new File(csvRootPath).listFiles();
        if (dir == null) throw new ExpectedDataTypeNotFoundException(EXCEPTION_MESSAGE);
        return dir;
    }
    private File getFile(String cryptoName) {
        File file = new File(csvRootPath + cryptoName + FILE_NAME_SUFFIX);
        if (!file.exists()) throw new ExpectedDataTypeNotFoundException(EXCEPTION_MESSAGE);
        return file;
    }
    private FileReader getFileReader(File file) {
        FileReader filereader;
        try {
            filereader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new ExpectedDataTypeNotFoundException(EXCEPTION_MESSAGE, e);
        }
        return filereader;
    }


}
