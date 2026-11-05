import { HttpClient } from "@angular/common/http";
import {inject, Injectable} from "@angular/core";
import { Observable } from "rxjs";

import{
    MovieDetails,
    MoviePage,
} from "../models/movie.models";

@Injectable({
    providedIn: "root"
})
export class MovieService {
    private readonly http = inject(HttpClient);
    private readonly apiUrl = "http://localhost:8080/api/movies";

    getPopularMovies(page = 1): Observable<MoviePage> {
     return this.http.get<MoviePage>(`${this.apiUrl}/popular`, {
       params: { page: page.toString() },
    });
  }

  searchMovies(query: string, page = 1): Observable<MoviePage> {
    return this.http.get<MoviePage>(`${this.apiUrl}/search`, {
        params: { query, page: page.toString() },
     });
  }

  getMovieDetails(movieId: number): Observable<MovieDetails> {
    return this.http.get<MovieDetails>(`${this.apiUrl}/${movieId}`);
  }
}