package com.movies.api.dto;

import com.movies.api.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieDto {
    private String title;
    private String genre;
    private String synopsis;
    private String format;
    private String schedule;
    private String duration;
    private float price;
    private Long roomId;

}
