package com.movies.api.controller;



import com.movies.api.dto.NewUser;

import com.movies.api.security.dto.JwtDto;
import com.movies.api.security.dto.LoginUser;
import com.movies.api.security.jwt.JwtProvider;
import com.movies.api.service.RolService;
import com.movies.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;



@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    UserService userService;




    @Operation(summary = "registro de usuarios/ user registration")
    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        return userService.createUser(newUser,bindingResult);
    }
    @Operation(summary = "inicio de sesión/ login")
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
      return userService.login(loginUser, bindingResult);
    }

}
