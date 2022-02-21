package com.movies.api.repository;

import com.movies.api.entity.Movie;
import com.movies.api.entity.Presentation;
import com.movies.api.entity.PresentationId;
import com.movies.api.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, PresentationId> {

    Optional<Presentation>findByIdAndAvailable(PresentationId id, boolean available);
    List<Presentation> findAllByIdAndAvailable(PresentationId id, boolean available);
    List<Presentation>findAllByMovieAndAvailable(Movie movie, boolean available);
    List<Presentation>findAllById_RoomIdAndAvailable(Long roomId, boolean available);
    boolean existsByMovieAndAvailable(Movie movie, boolean available);
}
