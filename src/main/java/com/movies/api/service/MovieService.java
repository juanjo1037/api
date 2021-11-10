package com.movies.api.service;


import com.movies.api.dto.Message;
import com.movies.api.dto.MovieDto;
import com.movies.api.entity.Movie;
import com.movies.api.entity.Room;
import com.movies.api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    RoomService roomService;

    public Iterable<Movie> findAll() {

        return movieRepository.findAll();
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public boolean existById(Long id) {
        return movieRepository.existsById(id);
    }

    public void save(Movie movie) {movieRepository.save(movie);}

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }



    public List<Movie> findByTitle(String title) { return movieRepository.findByTitle(title);}


    public ResponseEntity createMovie(MovieDto movieDto) {

        try{
            List<Room> rooms= roomService.findById(1L);
            Movie movie= new Movie(movieDto.getTitle(),
                    movieDto.getGenre(),
                    movieDto.getSynopsis(),
                    movieDto.getFormat(),
                    movieDto.getSchedule(),
                    movieDto.getDuration(),
                    movieDto.getPrice(),rooms
                    );
            movieRepository.save(movie);
            return new ResponseEntity<>(new Message("Pelicula creada"), HttpStatus.CREATED);

        }catch (Exception e) {
            return new ResponseEntity<>(new Message("La pelicula debe estar asignada a una sala existente"), HttpStatus.BAD_REQUEST);
        }

    }
}