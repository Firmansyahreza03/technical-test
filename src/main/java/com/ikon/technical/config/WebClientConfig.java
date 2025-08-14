package com.ikon.technical.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient dataWebClient(WebClient.Builder builder) {
        return builder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }
}
