package com.movies.api.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="movie",  schema = "cinema_manage")
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "genre")
    private String genre;
     @Column(name = "synopsis")
    private String synopsis;
     @Column(name = "format")
    private String format;
     @Column(name = "schedule")
    private String schedule;
     @Column(name = "duration")
    private String duration;
     @Column(name = "price")
    private float price;
    @Column(name = "billboard")
    private boolean billboard= false;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Reservation> reservationList;


    // como mapear el muchos muchos con room?

    @JoinTable(
            name = "presentation",
            joinColumns = @JoinColumn(name = "movie_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="room_id", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Room> rooms;



    public Movie(String title, String genre, String synopsis,
                 String format, String schedule, String duration, float price, List<Room> rooms) {
        this.title = title;
        this.genre = genre;
        this.synopsis = synopsis;
        this.format = format;
        this.schedule = schedule;
        this.duration = duration;
        this.price = price;
        this.rooms=rooms;
    }

    public Movie() {

    }

    public void addReservations(Reservation reservation){
        if(reservationList==null) reservationList=new ArrayList<>();
        reservationList.add(reservation);
        reservation.setMovie(this);
    }
}
