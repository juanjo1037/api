package com.movies.api.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReservedChairId implements Serializable {
    private static final long serialVersionUID = -1064367083897284663L;
    @Column(name = "chair_id", nullable = false)
    private Long chairId;
    @Column(name = "reservation_presentation_room_id", nullable = false)
    private Long reservationPresentationRoomId;
    @Column(name = "reservation_presentation_schedule", nullable = false, length = 45)
    private String reservationPresentationSchedule;
    @Column(name = "reservation_user_id", nullable = false)
    private Long reservationUserId;

    public ReservedChairId(Long chairId, Long reservationPresentationRoomId, String reservationPresentationSchedule, Long reservationUserId) {
        this.chairId = chairId;
        this.reservationPresentationRoomId = reservationPresentationRoomId;
        this.reservationPresentationSchedule = reservationPresentationSchedule;
        this.reservationUserId = reservationUserId;
    }

    public ReservedChairId() {

    }

    public Long getReservationUserId() {
        return reservationUserId;
    }

    public void setReservationUserId(Long reservationUserId) {
        this.reservationUserId = reservationUserId;
    }

    public String getReservationPresentationSchedule() {
        return reservationPresentationSchedule;
    }

    public void setReservationPresentationSchedule(String reservationPresentationSchedule) {
        this.reservationPresentationSchedule = reservationPresentationSchedule;
    }

    public Long getReservationPresentationRoomId() {
        return reservationPresentationRoomId;
    }

    public void setReservationPresentationRoomId(Long reservationPresentationRoomId) {
        this.reservationPresentationRoomId = reservationPresentationRoomId;
    }

    public Long getChairId() {
        return chairId;
    }

    public void setChairId(Long chairId) {
        this.chairId = chairId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationPresentationSchedule, chairId, reservationUserId, reservationPresentationRoomId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReservedChairId entity = (ReservedChairId) o;
        return Objects.equals(this.reservationPresentationSchedule, entity.reservationPresentationSchedule) &&
                Objects.equals(this.chairId, entity.chairId) &&
                Objects.equals(this.reservationUserId, entity.reservationUserId) &&
                Objects.equals(this.reservationPresentationRoomId, entity.reservationPresentationRoomId);
    }
}