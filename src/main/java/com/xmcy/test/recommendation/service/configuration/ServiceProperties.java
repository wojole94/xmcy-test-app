package com.xmcy.test.recommendation.service.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("recommendation.service")
@Data
public class ServiceProperties {
    String inputDataRoot;
}
