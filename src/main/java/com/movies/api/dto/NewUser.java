package com.movies.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class NewUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idCardType;

    private String idCard;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
