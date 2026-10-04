package com.movieholics.movieservice.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateMovieRequest(
        @NotBlank String title,
        @Size(max = 2000) String description,
        String posterUrl,
        String genre) {}