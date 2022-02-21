package com.movies.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MovieDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String genre;
    private String synopsis;
    private String format;
    private String image;
    private String duration;
    private Double price;
    private boolean billboard;
    private String backDropImg;
    private boolean comingSoon;

}
