package com.wellfair.movie_api.controller;

import com.wellfair.movie_api.model.TmdbMoviePage;
import com.wellfair.movie_api.service.TmdbMovieService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {

    private final TmdbMovieService tmdbMovieService;

    public MovieController(TmdbMovieService tmdbMovieService) {
        this.tmdbMovieService = tmdbMovieService;
    }

    @GetMapping("/popular")
    public TmdbMoviePage getPopularMovies(
            @RequestParam(defaultValue = "1") int page) {
        return tmdbMovieService.getPopularMovies(page);
    }

    @GetMapping("/search")
    public TmdbMoviePage searchMovies(
            @RequestParam String query,
            @RequestParam(defaultValue = "1") int page) {
        return tmdbMovieService.searchMovies(query, page);
    }
}