package com.movies.api.entity;




import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name="reservation",  schema = "cinema_manage")
public class Reservation implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "chairs_number")
    private int chairsNumber;
    @Column(name="price")
    private float price;


    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @JsonManagedReference
    @OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ReservedChair> reservedChairs;


    public Reservation() {

    }

    public Reservation( int chairsNumber, float price, User user, Movie movie, List<ReservedChair> chairs) {
        this.chairsNumber = chairsNumber;
        this.price = price;
        this.user = user;
        this.movie = movie;
        this.reservedChairs=chairs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getChairsNumber() {
        return chairsNumber;
    }

    public void setChairsNumber(int chairsNumber) {
        this.chairsNumber = chairsNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }


    public List<ReservedChair> getReservedChairs() {
        return reservedChairs;
    }

    public void setReservedChairs(List<ReservedChair> reservedChairs) {
        this.reservedChairs = reservedChairs;
    }
}
