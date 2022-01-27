package com.movies.api.dto;

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




    private String email;

    private Long idMovie;
    private Long idRoom;
    private String schedule;

    private int chairsNumber;

    private List<String> rows;

    private List<Integer> columns;

}
