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

    Optional<Presentation>findById(PresentationId id);
    List<Presentation> findAllById(PresentationId id);
    List<Presentation>findAllByMovie(Movie movie);
    List<Presentation>findAllById_RoomId(Long roomId);
    boolean existsByIdAndMovie(PresentationId id, Movie movie);

}
