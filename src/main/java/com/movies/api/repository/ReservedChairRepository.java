package com.movies.api.repository;

import com.movies.api.entity.ReservedChair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ReservedChairRepository extends JpaRepository<ReservedChair, Long> {

    @Override
    Optional<ReservedChair> findById(Long id);



}
