import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Observable } from 'rxjs';

import { Movie, MoviePage } from '../../models/movie.models';
import { MovieService } from '../../services/movie.service';

@Component({
  selector: 'app-movies-page',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './movies-page.component.html',
  styleUrl: './movies-page.component.css',
})
export class MoviesPageComponent implements OnInit {
  private readonly movieService = inject(MovieService);

  movies: Movie[] = [];
  searchQuery = '';
  isLoading = false;
  isSearching = false;
  noResults = false;
  errorMessage = '';
  currentPage = 1;
  totalPages = 1;

  ngOnInit(): void {
    this.loadPopularMovies();
  }

  loadPopularMovies(page = 1): void {
    this.isSearching = false;
    this.loadMovies(this.movieService.getPopularMovies(page));
  }

  searchMovies(page = 1): void {
    const query = this.searchQuery.trim();

    if (!query) {
      this.clearSearch();
      return;
    }

    this.isSearching = true;
    this.loadMovies(this.movieService.searchMovies(query, page));
  }

  clearSearch(): void {
    this.searchQuery = '';
    this.loadPopularMovies();
  }

  previousPage(): void {
    if (!this.isLoading && this.currentPage > 1) {
      this.changePage(this.currentPage - 1);
    }
  }

  nextPage(): void {
    if (!this.isLoading && this.currentPage < this.totalPages) {
      this.changePage(this.currentPage + 1);
    }
  }

  private changePage(page: number): void {
    if (this.isSearching) {
      this.searchMovies(page);
    } else {
      this.loadPopularMovies(page);
    }
  }

  private loadMovies(request: Observable<MoviePage>): void {
    this.isLoading = true;
    this.errorMessage = '';
    this.noResults = false;
    this.movies = [];

    request.subscribe({
      next: (response) => {
        this.movies = response.results;
        this.currentPage = response.page;
        this.totalPages = response.total_pages;
        this.noResults = this.movies.length === 0;
        this.isLoading = false;
      },
      error: () => {
        this.errorMessage = 'No fue posible cargar las películas. Intenta de nuevo.';
        this.isLoading = false;
      },
    });
  }
}