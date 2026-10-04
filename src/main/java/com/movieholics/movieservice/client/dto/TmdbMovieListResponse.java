package com.movieholics.movieservice.client.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record TmdbMovieListResponse(
        int page,
        List<TmdbMovie> results,
        @JsonProperty("total_pages") int totalPages) {}