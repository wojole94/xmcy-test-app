package com.xmcy.test.recommendation.service.configuration;


import com.xmcy.test.recommendation.service.readers.CryptoCsvFileReader;
import com.xmcy.test.recommendation.service.readers.CryptoInputDataReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    private static final String INPUT_DATA_ROOT = "data/";

    @Bean
    public CryptoInputDataReader initializeInputDataReader(){
        return new CryptoCsvFileReader(INPUT_DATA_ROOT);
    }
}
