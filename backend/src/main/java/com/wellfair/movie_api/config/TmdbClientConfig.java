package com.wellfair.movie_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration(proxyBeanMethods = false)
public class TmdbClientConfig {

    @Bean
    RestClient tmdbRestClient(
            RestClient.Builder builder,
            @Value("${tmdb.access-token}") String accessToken) {

        return builder
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeader(
                        HttpHeaders.AUTHORIZATION,
                        "Bearer " + accessToken)
                .defaultHeader(
                        HttpHeaders.ACCEPT,
                        MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}