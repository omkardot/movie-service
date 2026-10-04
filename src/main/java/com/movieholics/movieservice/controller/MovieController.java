package com.movieholics.movieservice.controller;

import com.movieholics.movieservice.dto.CreateMovieRequest;
import com.movieholics.movieservice.dto.ExternalMovieResponse;
import com.movieholics.movieservice.dto.MovieResponse;
import com.movieholics.movieservice.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponse> getAll() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public MovieResponse getById(@PathVariable Long id) {
        return movieService.getMovie(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieResponse create(@Valid @RequestBody CreateMovieRequest request) {
        return movieService.createMovie(request);
    }
    @GetMapping("/popular")
    public List<ExternalMovieResponse> popular(@RequestParam(defaultValue = "1") int page) {
        return movieService.getPopularMovies(page);
    }

    @GetMapping("/search")
    public List<ExternalMovieResponse> search(@RequestParam String query,
                                              @RequestParam(defaultValue = "1") int page) {
        return movieService.searchMovies(query, page);
    }
}