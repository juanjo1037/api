package com.movies.api.repository;

import com.movies.api.entity.Reservation;
import com.movies.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;



@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Override
    Optional<Reservation> findById(Long id);

    @Override
    boolean existsById(Long id );

    List<Reservation>findAllByUser(User user);


}
