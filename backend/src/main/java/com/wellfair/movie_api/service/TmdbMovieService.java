package com.wellfair.movie_api.service;

import com.wellfair.movie_api.model.TmdbMovieDetails;
import com.wellfair.movie_api.model.TmdbMoviePage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class TmdbMovieService {

    private static final String LANGUAGE = "es-MX";

    private final RestClient tmdbRestClient;

    public TmdbMovieService(RestClient tmdbRestClient) {
        this.tmdbRestClient = tmdbRestClient;
    }

    public TmdbMoviePage getPopularMovies(int page) {
        return tmdbRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/popular")
                        .queryParam("language", LANGUAGE)
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .body(TmdbMoviePage.class);
    }

    public TmdbMoviePage searchMovies(String query, int page) {
        return tmdbRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("query", query)
                        .queryParam("language", LANGUAGE)
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .body(TmdbMoviePage.class);
    }

    public TmdbMovieDetails getMovieDetails(int movieId) {
        return tmdbRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}")
                        .queryParam("language", LANGUAGE)
                        .build(movieId))
                .retrieve()
                .body(TmdbMovieDetails.class);
    }
}