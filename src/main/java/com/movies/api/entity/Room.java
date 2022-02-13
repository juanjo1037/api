package com.movies.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "room")
@Entity
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "rows_number", nullable = false)
    private int rowsNumber;

    @Column(name = "columns_number", nullable = false)
    private int columnsNumber;


    public Room(int capacity, int rowsNumber, int columnsNumber) {
        this.capacity = capacity;
        this.rowsNumber = rowsNumber;
        this.columnsNumber = columnsNumber;
    }

    public Room() {

    }
}