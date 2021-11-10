package com.movies.api.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PresentationId implements Serializable {
    private static final long serialVersionUID = 7581394908909728342L;
    @Column(name = "movie_id", nullable = false)
    private Long movieId;
    @Column(name = "room_id", nullable = false)
    private Long roomId;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, roomId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PresentationId entity = (PresentationId) o;
        return Objects.equals(this.movieId, entity.movieId) &&
                Objects.equals(this.roomId, entity.roomId);
    }
}