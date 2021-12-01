package com.movies.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class NewUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotBlank
    private String documentType;
    @NotBlank
    private String document;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    private String email;
    @NotBlank
    private String password;




    public String getDocumentType() {
        return documentType;
    }



    public String getDocument() {
        return document;
    }



    public String getFirstName() {
        return firstName;
    }



    public String getLastName() {
        return lastName;
    }



    public String getEmail() {
        return email;
    }



    public String getPassword() {
        return password;
    }




}
