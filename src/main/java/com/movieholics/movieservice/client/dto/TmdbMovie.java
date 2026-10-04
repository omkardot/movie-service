package com.movieholics.movieservice.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record TmdbMovie(
        Long id,
        String title,
        String overview,
        @JsonProperty("poster_path") String posterPath,
        @JsonProperty("genre_ids") List<Integer> genreIds) {}

