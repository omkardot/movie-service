package com.movieholics.movieservice.entity;



import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    private String posterUrl;

    private String genre;

    protected Movie() {}   // required by JPA

    public Movie(String title, String description, String posterUrl, String genre) {
        this.title = title;
        this.description = description;
        this.posterUrl = posterUrl;
        this.genre = genre;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPosterUrl() { return posterUrl; }
    public String getGenre() { return genre; }

    public void update(String title, String description, String posterUrl, String genre) {
        this.title = title;
        this.description = description;
        this.posterUrl = posterUrl;
        this.genre = genre;
    }
}