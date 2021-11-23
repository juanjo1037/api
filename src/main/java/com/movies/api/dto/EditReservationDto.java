package com.movies.api.dto;


import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class EditReservationDto  implements Serializable {
    private static final long serialVersionUID = 1L;

   private List<Long> chairsToDelete;

}
