package com.movies.api.service;


import com.movies.api.entity.User;
import com.movies.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {


    @Autowired
    UserRepository userRepository;

    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);

    }
    public boolean existsByEmail(String email){

        return userRepository.existsByEmail(email);
    }
    public void save(User user){
        userRepository.save(user);
    }

}
