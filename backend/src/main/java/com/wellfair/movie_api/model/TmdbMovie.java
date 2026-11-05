package com.wellfair.movie_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TmdbMovie(
        int id,
        String title,
        String overview,
        @JsonProperty("poster_path") String posterPath,
        @JsonProperty("release_date") String releaseDate,
        @JsonProperty("vote_average") double voteAverage,
        @JsonProperty("genre_ids") List<Integer> genreIds
) {
}