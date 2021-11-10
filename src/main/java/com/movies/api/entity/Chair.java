package com.movies.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "chair",  schema = "cinema_manage")
@Getter
@Setter
public class Chair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    private int row;
    private int column;
    private boolean reserved= false;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;



}
