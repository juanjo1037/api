package com.movies.api.service;


import com.movies.api.dto.NewUser;
import com.movies.api.entity.Role;
import com.movies.api.entity.User;
import com.movies.api.enums.RolName;
import com.movies.api.repository.UserRepository;
import com.movies.api.security.dto.JwtDto;
import com.movies.api.security.dto.LoginUser;
import com.movies.api.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {


    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RolService rolService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;
    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);

    }
    public boolean existsByEmail(String email){

        return userRepository.existsByEmail(email);
    }
    public void save(User user){
        userRepository.save(user);
    }

    public boolean existsByDocument(String document){

        return userRepository.existsByDocument(document);
    }
    public ResponseEntity createUser(NewUser newUser, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity("Campos mal puestos", HttpStatus.BAD_REQUEST);
        if(this.existsByDocument(newUser.getDocument()))
            return new ResponseEntity("Ya existe un usuario con ese documento", HttpStatus.BAD_REQUEST);
        if(this.existsByEmail(newUser.getEmail()))
            return new ResponseEntity("Ya existe un usuario con ese correo", HttpStatus.BAD_REQUEST);


        User user= new User(newUser.getDocumentType(),newUser.getDocument(),newUser.getFirstName(),newUser.getLastName(),
                newUser.getEmail(),passwordEncoder.encode(newUser.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
        user.setRoles(roles);
       this.save(user);
        return new ResponseEntity("Usuario Creado", HttpStatus.CREATED);
    }
    public ResponseEntity<JwtDto> login(LoginUser loginUser, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity("Email o contraseña incorrectos", HttpStatus.BAD_REQUEST);
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getEmail(),loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt =  jwtProvider.generateToken(authentication);
        JwtDto jwtDto= new JwtDto(jwt);
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }
}
