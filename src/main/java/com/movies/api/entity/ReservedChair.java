package com.movies.api.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Table(name = "reserved_chair", indexes = {
        @Index(name = "fk_reserved_chair_reservation1_idx", columnList = "reservation_presentation_room_id, reservation_presentation_schedule, reservation_user_id"),
        @Index(name = "fk_reserved_chair_chair1_idx", columnList = "chair_id")
})
@Entity
public class ReservedChair {
    @EmbeddedId
    private ReservedChairId id;


    public ReservedChair(ReservedChairId id) {
        this.id = id;
    }

    public ReservedChair() {

    }

    public ReservedChairId getId() {
        return id;
    }



    public void setId(ReservedChairId id) {
        this.id = id;
    }
}