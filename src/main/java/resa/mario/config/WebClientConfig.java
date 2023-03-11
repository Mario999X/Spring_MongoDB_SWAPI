package resa.mario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

// https://mercyjemosop.medium.com/consume-external-api-spring-boot-7e2c684e3d00
// https://stackoverflow.com/questions/42365266/call-another-rest-api-from-my-server-in-spring-boot

@Configuration
public class WebClientConfig {

    private static final String API_URL = "https://swapi.dev/api/";

    @Bean
    public WebClient webclient() {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();

        return WebClient
                .builder()
                .baseUrl(API_URL)
                .exchangeStrategies(strategies)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
