package com.ddd.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Value("${config.webclient.baseurl.user}")
    private String userBaseUrl;

    @Bean
    public WebClient registerWebClient() {
        return WebClient.create(userBaseUrl);
    }

}
