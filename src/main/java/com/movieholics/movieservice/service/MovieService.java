package com.movieholics.movieservice.service;

import com.movieholics.movieservice.client.dto.TmdbClient;
import com.movieholics.movieservice.client.dto.TmdbMovieListResponse;
import com.movieholics.movieservice.config.TmdbProperties;
import com.movieholics.movieservice.dto.CreateMovieRequest;
import com.movieholics.movieservice.dto.ExternalMovieResponse;
import com.movieholics.movieservice.dto.MovieResponse;
import com.movieholics.movieservice.entity.Movie;
import com.movieholics.movieservice.mapper.MovieMapper;
import com.movieholics.movieservice.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final TmdbClient tmdbClient;
    private final TmdbProperties tmdbProperties;
    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper, TmdbClient tmdbClient, TmdbProperties tmdbProperties) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.tmdbClient = tmdbClient;
        this.tmdbProperties = tmdbProperties;
    }

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream().map(movieMapper::toResponse).toList();
    }

    public MovieResponse getMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found: " + id));
        return movieMapper.toResponse(movie);
    }

    public MovieResponse createMovie(CreateMovieRequest request) {
        Movie saved = movieRepository.save(movieMapper.toEntity(request));
        return movieMapper.toResponse(saved);
    }


    public List<ExternalMovieResponse> getPopularMovies(int page) {
        return toExternalResponses(tmdbClient.getPopular(page));
    }

    public List<ExternalMovieResponse> searchMovies(String query, int page) {
        return toExternalResponses(tmdbClient.search(query, page));
    }

    private List<ExternalMovieResponse> toExternalResponses(TmdbMovieListResponse response) {
        Map<Integer, String> genres = tmdbClient.getGenres();
        return response.results().stream()
                .map(m -> new ExternalMovieResponse(
                        m.id(),
                        m.title(),
                        m.overview(),
                        m.posterPath() == null ? null : tmdbProperties.imageBaseUrl() + m.posterPath(),
                        m.genreIds().stream().map(genres::get).filter(Objects::nonNull).toList()))
                .toList();
    }
}