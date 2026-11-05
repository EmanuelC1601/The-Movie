export interface Movie {
  id: number;
  title: string;
  overview: string;
  poster_path: string | null;
  release_date: string;
  vote_average: number;
  genre_ids: number[];
}

export interface MoviePage {
  page: number;
  results: Movie[];
  total_pages: number;
  total_results: number;
}

export interface MovieGenre {
  id: number;
  name: string;
}

export interface MovieDetails {
  id: number;
  title: string;
  overview: string;
  poster_path: string | null;
  release_date: string;
  vote_average: number;
  runtime: number | null;
  genres: MovieGenre[];
}

export interface ApiError {
  timestamp: string;
  status: number;
  message: string;
}