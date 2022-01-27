package com.movies.api.service;


import com.movies.api.entity.Role;
import com.movies.api.enums.RolName;
import com.movies.api.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {
    @Autowired
    RolRepository rolRepository;

    public Optional<Role> getByRolName(RolName rolName){
        return rolRepository.findByRoleName(rolName);

    }
}
