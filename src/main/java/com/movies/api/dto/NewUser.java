package com.movies.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewUser {
    private String idCardType;

    private String idCard;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
