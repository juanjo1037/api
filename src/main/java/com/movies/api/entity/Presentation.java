package com.movies.api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table(name = "presentation", indexes = {
        @Index(name = "fk_movie_has_room_room1_idx", columnList = "room_id"),
        @Index(name = "fk_movie_has_room_movie1_idx", columnList = "movie_id")
})
@Entity
public class Presentation {
    @EmbeddedId
    private PresentationId id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roomId")
    @JoinColumn(name = "room_id")
    private Room room;

    @JsonManagedReference
    @OneToMany(mappedBy = "presentation", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Reservation> reservations;

    public Presentation() {

    }

    public Presentation(PresentationId id, Movie movie, Room room) {
        this.id = id;
        this.movie = movie;
        this.room = room;
    }

    public PresentationId getId() {
        return id;
    }

    public void setId(PresentationId id) {
        this.id = id;
    }

}
