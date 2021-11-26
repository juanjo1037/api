package com.movies.api.controller;


import com.movies.api.dto.Message;
import com.movies.api.dto.NewUser;
import com.movies.api.entity.Role;
import com.movies.api.entity.User;
import com.movies.api.enums.RolName;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
   PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @Operation(summary = "registro de usuarios")
    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
            if (bindingResult.hasErrors())
                return new ResponseEntity("Campos mal puestos", HttpStatus.BAD_REQUEST);
            if(userService.existsByDocument(newUser.getDocument()))
                return new ResponseEntity("Ya existe un usuario con ese documento", HttpStatus.BAD_REQUEST);
            if(userService.existsByEmail(newUser.getEmail()))
                return new ResponseEntity("Ya existe un usuario con ese correo", HttpStatus.BAD_REQUEST);


        User user= new User(newUser.getDocumentType(),newUser.getDocument(),newUser.getFirstName(),newUser.getLastName(),
                newUser.getEmail(),passwordEncoder.encode(newUser.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity("Usuario Creado", HttpStatus.CREATED);
    }
    @Operation(summary = "inicio de sesión")
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity("Email o contraseña incorrectos", HttpStatus.BAD_REQUEST);
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getEmail(),loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt =  jwtProvider.generateToken(authentication);
        UserDetails userDetails= (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto= new JwtDto(jwt, userDetails.getUsername(),userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

}
