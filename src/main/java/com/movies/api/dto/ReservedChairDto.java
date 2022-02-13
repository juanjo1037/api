package com.movies.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservedChairDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long presentationRoomId;
    private String presentationSchedule;
    private Long userId;
}
