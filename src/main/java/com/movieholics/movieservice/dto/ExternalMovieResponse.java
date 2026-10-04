package com.movieholics.movieservice.dto;


import java.util.List;

public record ExternalMovieResponse(
        Long tmdbId, String title, String description, String posterUrl, List<String> genres) {}
