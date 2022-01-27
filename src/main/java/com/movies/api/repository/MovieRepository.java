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



    List<Movie>findMoviesByBillboard(boolean billboard);

    List<Movie> findByTitle(String title);

    Optional<Movie> findByTitleAndFormat(String title, String format);

    boolean existsByTitleAndFormat(String title, String format);

    List<Movie> findAllByGenreContaining(String genre);



}
