package com.movies.api.repository;

import com.movies.api.entity.Movie;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;



@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Override
    @NotNull
    Optional<Movie> findById(@NotNull Long id);

    @Override
    boolean existsById(@NotNull Long id );

    Optional<Movie> findByIdAndDeleted(@NotNull Long id, boolean deleted);

    List<Movie>findMoviesByBillboardAndDeleted(boolean billboard, boolean deleted);
    List<Movie>findAllByDeleted(boolean deleted);
    List<Movie> findByTitleAndDeleted(String title, boolean deleted);

    Optional<Movie> findByTitleAndFormatAndDeleted(String title, String format, boolean deleted);

    boolean existsByTitleAndFormatAndDeleted(String title, String format, boolean deleted);

    List<Movie> findAllByGenreContainingAndDeleted(String genre, boolean deleted);

        List<Movie>findMoviesByComingSoonAndDeleted(boolean isComingSoon, boolean deleted);

}
