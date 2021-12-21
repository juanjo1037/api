package com.movies.api.dto;

import com.movies.api.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String genre;
    private String synopsis;
    private String format;
    private String duration;
    private float price;
    private boolean billboard;


}
