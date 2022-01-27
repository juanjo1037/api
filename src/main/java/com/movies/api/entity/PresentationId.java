package com.movies.api.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PresentationId implements Serializable {
    private static final long serialVersionUID = -3519220752987376316L;
    @Column(name = "room_id", nullable = false)
    private Long roomId;
    @Column(name = "schedule", nullable = false, length = 45)
    private String schedule;

    public PresentationId() {

    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public PresentationId(Long roomId, String schedule) {
        this.roomId = roomId;
        this.schedule = schedule;
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedule, roomId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PresentationId entity = (PresentationId) o;
        return Objects.equals(this.schedule, entity.schedule) &&
                Objects.equals(this.roomId, entity.roomId);
    }
}