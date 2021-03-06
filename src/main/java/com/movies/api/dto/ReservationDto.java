package com.movies.api.dto;

import com.movies.api.entity.Movie;
import com.movies.api.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationDto implements Serializable {
    private static final long serialVersionUID = 1L;



    private float price;

    private User user;

    private String movieTitle;
    private String movieFormat;

    private int chairsNumber;

    private List<Integer> rows;

    private List<Integer> columns;

}
