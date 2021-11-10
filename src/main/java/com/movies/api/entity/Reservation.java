package com.movies.api.entity;


import lombok.Getter;
import lombok.Setter;//Lombok evita hacer getters y setters

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name="reservation",  schema = "cinema_manage")
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Timestamp date;
    private int chairsNumber;
    private float price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "reservation", fetch=FetchType.LAZY)
    private List<Chair> chairs;



    public Reservation() {

    }

    public Reservation( int chairsNumber, float price, User user, Movie movie) {
        this.chairsNumber = chairsNumber;
        this.price = price;
        this.user = user;
        this.movie = movie;
    }


}
