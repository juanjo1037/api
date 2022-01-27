package com.movies.api.repository;

import com.movies.api.entity.Role;
import com.movies.api.enums.RolName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(RolName rolName);
}
