package com.movieholics.movieservice.dto;


public record MovieResponse(Long id, String title, String description, String posterUrl, String genre) {}
