package com.movies.api.security.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;

import java.util.Collection;

public class JwtDto {
    private String token;


    public JwtDto(String token) {
        this.token = token;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
