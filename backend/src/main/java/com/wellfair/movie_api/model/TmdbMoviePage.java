package com.wellfair.movie_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TmdbMoviePage(
        int page,
        List<TmdbMovie> results,
        @JsonProperty("total_pages") int totalPages,
        @JsonProperty("total_results") int totalResults
) {
}