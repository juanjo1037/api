package com.movies.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "chair",  schema = "cinema_manage")
public class Chair implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name="row")
    private int row;
    @Column(name="column")
    private int column;
    @Column(name = "reserved")
    private boolean reserved= false;
    @JsonBackReference
    @OneToOne(mappedBy = "chair", orphanRemoval = true, fetch = FetchType.LAZY)
    private ReservedChair reservedChair;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }


    public ReservedChair getReservedChair() {
        return reservedChair;
    }

    public void setReservedChair(ReservedChair reservedChair) {
        this.reservedChair = reservedChair;
    }
}
