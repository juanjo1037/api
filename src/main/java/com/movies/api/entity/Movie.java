package com.movies.api.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="movie",  schema = "cinema_manage")
public class Movie implements Serializable {
    private static final long serialVersionUID = 6489021462409984216L;

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
     @Column(name = "duration")
    private String duration;
     @Column(name = "price")
    private float price;
    @Column(name = "billboard")
    private boolean billboard= true;




    @JsonBackReference
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Presentation> presentations;


    public Movie(String title, String genre, String synopsis,
                 String format, String schedule, String duration, float price, List<Room> rooms) {
        this.title = title;
        this.genre = genre;
        this.synopsis = synopsis;
        this.format = format;
        this.duration = duration;
        this.price = price;

    }

    public Movie() {

    }

    public Movie(String title, String genre, String synopsis, String format, String schedule, String duration, float price)
    {

        this.title = title;
        this.genre = genre;
        this.synopsis = synopsis;
        this.format = format;
        this.duration = duration;
        this.price = price;
    }

    public Movie(String title, String genre, String synopsis, String format, String duration, float price, List<Room> rooms) {

    }

    public Movie( String title, String genre, String synopsis, String format, String duration, float price, boolean billboard) {

        this.title = title;
        this.genre = genre;
        this.synopsis = synopsis;
        this.format = format;
        this.duration = duration;
        this.price = price;
        this.billboard = billboard;
    }

    public List<Presentation> getPresentations() {
        return presentations;
    }

    public void setPresentations(List<Presentation> presentations) {
        this.presentations = presentations;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }



    public boolean isBillboard() {
        return billboard;
    }

    public void setBillboard(boolean billboard) {
        this.billboard = billboard;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }




}
