package com.movieholics.movieservice.mapper;


import com.movieholics.movieservice.dto.CreateMovieRequest;
import com.movieholics.movieservice.dto.MovieResponse;
import com.movieholics.movieservice.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieResponse toResponse(Movie movie) {
        return new MovieResponse(movie.getId(), movie.getTitle(), movie.getDescription(),
                movie.getPosterUrl(), movie.getGenre());
    }

    public Movie toEntity(CreateMovieRequest request) {
        return new Movie(request.title(), request.description(), request.posterUrl(), request.genre());
    }
}
