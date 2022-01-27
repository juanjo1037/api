package com.movies.api.entity;

import javax.persistence.*;

@Table(name = "presentation", indexes = {
        @Index(name = "fk_movie_has_room_room1_idx", columnList = "room_id"),
        @Index(name = "fk_presentation_movie1_idx", columnList = "movie_id")
})
@Entity
public class Presentation {
    @EmbeddedId
    private PresentationId id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    public Presentation() {

    }

    public Movie getMovie() {
        return movie;
    }

    public Presentation(PresentationId id, Movie movie) {
        this.id = id;
        this.movie = movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public PresentationId getId() {
        return id;
    }

    public void setId(PresentationId id) {
        this.id = id;
    }
}