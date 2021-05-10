package com.crypto.ficc.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "ticker.url")
@Getter
@Setter
public class TickerApiProperties {

    private String host;
    private Map<String, String> endpoints;
}
