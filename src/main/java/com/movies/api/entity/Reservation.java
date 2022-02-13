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
    private double price;

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name="presentation_schedule", referencedColumnName="schedule",insertable = false,updatable = false),
            @JoinColumn(name="presentation_room_id", referencedColumnName="room_id",insertable = false,updatable = false)
    })
    private Presentation presentation;

    public Reservation(ReservationId id, int chairsNumber, double price) {
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