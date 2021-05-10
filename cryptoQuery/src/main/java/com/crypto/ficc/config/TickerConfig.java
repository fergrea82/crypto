package com.crypto.ficc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TickerConfig {

    @Bean
    public TickerApiProperties tickerApi () {
        return new TickerApiProperties();
    }

    @Bean
    public RestOperations restOperations() {
        return new RestTemplate();
    }

}
