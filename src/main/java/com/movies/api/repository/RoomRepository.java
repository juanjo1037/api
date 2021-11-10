package com.movies.api.repository;

import com.movies.api.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

    @Override
    Optional<Room> findById(Long id);

    List<Room> findByCapacity(int capacity);
}
