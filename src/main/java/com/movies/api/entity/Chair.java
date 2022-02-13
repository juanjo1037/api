package com.movies.api.entity;

import javax.persistence.*;

@Table(name = "chair", indexes = {
        @Index(name = "fk_chair_room1_idx", columnList = "room_id")
})
@Entity
public class Chair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "chair_row", nullable = false)
    private String row;

    @Column(name = "chair_column", nullable = false)
    private Integer column;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;


    public Chair(String row, Integer column, Room room) {
        this.row = row;
        this.column = column;
        this.room = room;
    }

    public Chair() {

    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}