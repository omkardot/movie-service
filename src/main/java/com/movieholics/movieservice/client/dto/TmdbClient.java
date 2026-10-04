package com.movieholics.movieservice.client.dto;


import com.movieholics.movieservice.client.dto.TmdbGenreResponse;
import com.movieholics.movieservice.client.dto.TmdbMovieListResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TmdbClient {

    private final RestClient restClient;
    private volatile Map<Integer, String> genreCache;

    public TmdbClient(RestClient tmdbRestClient) {
        this.restClient = tmdbRestClient;
    }

    public TmdbMovieListResponse getPopular(int page) {
        return restClient.get()
                .uri("/movie/popular?page={page}", page)
                .retrieve()
                .body(TmdbMovieListResponse.class);
    }

    public TmdbMovieListResponse search(String query, int page) {
        return restClient.get()
                .uri(uri -> uri.path("/search/movie")
                        .queryParam("query", query)
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .body(TmdbMovieListResponse.class);
    }

    public Map<Integer, String> getGenres() {
        if (genreCache == null) {
            TmdbGenreResponse response = restClient.get()
                    .uri("/genre/movie/list")
                    .retrieve()
                    .body(TmdbGenreResponse.class);
            genreCache = response.genres().stream()
                    .collect(Collectors.toMap(TmdbGenreResponse.Genre::id, TmdbGenreResponse.Genre::name));
        }
        return genreCache;
    }
}