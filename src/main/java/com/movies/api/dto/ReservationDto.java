package com.movies.api.dto;

import com.movies.api.entity.Movie;
import com.movies.api.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationDto {

    @Min(1)
    private int chairsNumber;
    @Min(0)
    private float price;

    private User user;

    private Movie movie;


}
