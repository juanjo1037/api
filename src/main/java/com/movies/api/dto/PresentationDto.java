package com.movies.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PresentationDto {

    private Long idRoom;
    private Long idMovie;
    private String schedule;

}
