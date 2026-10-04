package com.movieholics.movieservice.client.dto;


import java.util.List;

public record TmdbGenreResponse(List<Genre> genres) {
    public record Genre(int id, String name) {}
}