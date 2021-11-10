package com.movies.api.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Table(name = "presentation", indexes = {
        @Index(name = "fk_movie_has_room_room1_idx", columnList = "room_id"),
        @Index(name = "fk_movie_has_room_movie1_idx", columnList = "movie_id")
})
@Entity
public class Presentation {
    @EmbeddedId
    private PresentationId id;

    public PresentationId getId() {
        return id;
    }

    public void setId(PresentationId id) {
        this.id = id;
    }
}