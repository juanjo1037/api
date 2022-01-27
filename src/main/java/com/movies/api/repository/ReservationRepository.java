package com.movies.api.repository;

import com.movies.api.entity.Reservation;
import com.movies.api.entity.ReservationId;
import com.movies.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {

    @Override
    Optional<Reservation> findById(ReservationId id);

    @Override
    boolean existsById(ReservationId id );

    List<Reservation>findAllById_UserId(Long userId);


}
