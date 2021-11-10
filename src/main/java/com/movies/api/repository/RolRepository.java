package com.movies.api.repository;

import com.movies.api.entity.Rol;
import com.movies.api.enums.RolName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByRolName(RolName rolName);
}
