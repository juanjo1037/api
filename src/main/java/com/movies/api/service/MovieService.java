package com.movies.api.service;


import com.movies.api.dto.MovieDto;
import com.movies.api.entity.Movie;
import com.movies.api.entity.Room;
import com.movies.api.repository.MovieRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;



@Service
@Transactional
public class MovieService {

    @Autowired
    MovieRepository movieRepository;



    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public boolean existById(Long id) {
        return !movieRepository.existsById(id);
    }



    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie>findMoviesByBillboard(){
            return movieRepository.findMoviesByBillboard(true);
        }

    public List<Movie>findAll(){
        return movieRepository.findAll();
    }
    public List<Movie> findByTitle(String title) { return movieRepository.findByTitle(title);}

    public Optional<Movie>findByTitleAndFormat(String title, String format){
        return movieRepository.findByTitleAndFormat(title,format);
    }
    public ResponseEntity<String> createMovie(@NotNull MovieDto movieDto, Optional<Room> optRoom) {

            List<Room> rooms = new ArrayList<>();
            optRoom.ifPresent(rooms::add);
            Movie movie= new Movie(
                    movieDto.getTitle(),
                    movieDto.getGenre(),
                    movieDto.getSynopsis(),
                    movieDto.getFormat(),
                    movieDto.getSchedule(),
                    movieDto.getDuration(),
                    movieDto.getPrice(),rooms);

            movieRepository.save(movie);
            return new ResponseEntity<>("pelicula creada", HttpStatus.CREATED);

    }
    public ResponseEntity<String> updateMovie(Long id, MovieDto movieDto){
        Movie movie= movieRepository.findById(id).get();
        movie.setTitle(movieDto.getTitle());
        movie.setGenre(movieDto.getGenre());
        movie.setSynopsis(movieDto.getSynopsis());
        movie.setFormat(movieDto.getFormat());
        movie.setSchedule(movieDto.getSchedule());
        movie.setPrice(movieDto.getPrice());
        return new ResponseEntity<>("Pelicula Actualizada", HttpStatus.OK);

    }

}