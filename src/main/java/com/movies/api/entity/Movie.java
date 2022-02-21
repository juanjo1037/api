package com.movies.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "movie")
@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Column(name = "genre", nullable = false, length = 45)
    private String genre;

    @Column(name = "synopsis", nullable = false, length = 200)
    private String synopsis;

    @Column(name = "image", length = 250)
    private String image;

    @Column(name = "format", nullable = false, length = 45)
    private String format;

    @Column(name = "duration", nullable = false, length = 20)
    private String duration;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "billboard", nullable = false)
    private boolean billboard = false;

    @Column(name = "backdrop_img", length = 300)
    private String backDropImg;

    @Column(name = "coming_soon" )
    private boolean comingSoon=false;

    @Column(name = "is_deleted")
    private boolean deleted=false;


    public Movie(String title, String genre, String synopsis, String image, String format, String duration, Double price, boolean billboard, String backDropImg, boolean comingSoon) {
        this.title = title;
        this.genre = genre;
        this.synopsis = synopsis;
        this.image = image;
        this.format = format;
        this.duration = duration;
        this.price = price;
        this.billboard = billboard;
        this.backDropImg = backDropImg;
        this.comingSoon = comingSoon;
    }

    public Movie() {

    }

}