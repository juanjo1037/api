package com.movies.api.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReservationId implements Serializable {
    private static final long serialVersionUID = -526950117055174072L;
    @Column(name = "presentation_room_id", nullable = false)
    private Long presentationRoomId;
    @Column(name = "presentation_schedule", nullable = false, length = 45)
    private String presentationSchedule;
    @Column(name = "user_id", nullable = false)
    private Long userId;

    public ReservationId(Long presentationRoomId, String presentationSchedule, Long userId) {
        this.presentationRoomId = presentationRoomId;
        this.presentationSchedule = presentationSchedule;
        this.userId = userId;
    }

    public ReservationId() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPresentationSchedule() {
        return presentationSchedule;
    }

    public void setPresentationSchedule(String presentationSchedule) {
        this.presentationSchedule = presentationSchedule;
    }

    public Long getPresentationRoomId() {
        return presentationRoomId;
    }

    public void setPresentationRoomId(Long presentationRoomId) {
        this.presentationRoomId = presentationRoomId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(presentationSchedule, userId, presentationRoomId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReservationId entity = (ReservationId) o;
        return Objects.equals(this.presentationSchedule, entity.presentationSchedule) &&
                Objects.equals(this.userId, entity.userId) &&
                Objects.equals(this.presentationRoomId, entity.presentationRoomId);
    }
}