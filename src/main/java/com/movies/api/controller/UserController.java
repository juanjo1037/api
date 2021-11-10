package com.movies.api.controller;


import com.movies.api.dto.Message;
import com.movies.api.dto.NewUser;
import com.movies.api.entity.User;
import com.movies.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody NewUser newUser){


        User user= new User(newUser.getIdCardType(),newUser.getIdCard(),newUser.getFirstName(),newUser.getLastName(),
                newUser.getEmail(),newUser.getPassword());
        userService.save(user);
        return new ResponseEntity<>(new Message("reserva creada"), HttpStatus.OK);
    }

}
