package com.xmcy.test.recommendation.service.configuration;


import com.xmcy.test.recommendation.service.readers.CryptoCsvFileReader;
import com.xmcy.test.recommendation.service.readers.CryptoInputDataReader;
import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ServiceConfiguration {
    private ServiceProperties properties;
    @Bean
    public CryptoInputDataReader initializeInputDataReader(){
        return new CryptoCsvFileReader(properties.getInputDataRoot());
    }
}
