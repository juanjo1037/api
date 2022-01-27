package com.movies.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "reservation", indexes = {
        @Index(name = "fk_reservation_user1_idx", columnList = "user_id"),
        @Index(name = "fk_reservation_presentation1_idx", columnList = "presentation_room_id, presentation_schedule")
})
@Getter
@Setter
@Entity
public class Reservation {
    @EmbeddedId
    private ReservationId id;
    @Column(name = "chairs_number", nullable = false)
    private int chairsNumber;
    @Column(name = "price", nullable = false)
    private Double price;

    public Reservation(ReservationId id, int chairsNumber, Double price) {
        this.id = id;
        this.chairsNumber = chairsNumber;
        this.price = price;
    }

    public Reservation() {

    }

    public ReservationId getId() {
        return id;
    }

    public void setId(ReservationId id) {
        this.id = id;
    }
}