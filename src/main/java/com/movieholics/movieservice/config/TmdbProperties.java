package com.movieholics.movieservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tmdb")
public record TmdbProperties(String baseUrl, String imageBaseUrl, String token) {}