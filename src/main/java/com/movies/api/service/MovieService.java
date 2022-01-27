package com.movies.api.service;


import com.movies.api.dto.MovieDto;
import com.movies.api.entity.Movie;
import com.movies.api.repository.MovieRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;



@Service
@Transactional
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public Optional<Movie> findById(Long id){
        return movieRepository.findById(id);
    }



    public boolean existById(Long id) {
        return movieRepository.existsById(id);
    }



    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie>findMoviesByBillboard(){
            List<Movie> movies = movieRepository.findMoviesByBillboard(true);
        Collections.shuffle(movies);
        return movies;
        }

    public List<Movie>findAll(){
        return movieRepository.findAll();
    }
    public List<Movie> findByTitle(String title) { return movieRepository.findByTitle(title);}

    public Optional<Movie>findByTitleAndFormat(String title, String format){
        return movieRepository.findByTitleAndFormat(title,format);
    }
    public boolean existsByTitleAndFormat(String title, String format){
        return movieRepository.existsByTitleAndFormat(title, format);
    }
    public ResponseEntity<String> createMovie(@NotNull MovieDto movieDto) {

    if (!this.existsByTitleAndFormat(movieDto.getTitle(), movieDto.getFormat())){
            Movie movie= new Movie(
                    movieDto.getTitle(),
                    movieDto.getGenre(),
                    movieDto.getSynopsis(),
                    movieDto.getImage(),
                    movieDto.getFormat(),
                    movieDto.getDuration(),
                    movieDto.getPrice(),
                    movieDto.isBillboard(),
                    movieDto.getBackDropImg());

            movieRepository.save(movie);
            return new ResponseEntity<>("pelicula creada", HttpStatus.CREATED);
    }else
        return new ResponseEntity<>("La pelicula ya existe", HttpStatus.BAD_REQUEST);

    }
    public ResponseEntity<String> updateMovie(Long id, MovieDto movieDto){
        if (!movieRepository.findById(id).isPresent()){
            return new ResponseEntity<>("no hay una pelicula con ese ID", HttpStatus.NOT_FOUND);
        }else{
        Movie movie= movieRepository.findById(id).get();
        movie.setTitle(movieDto.getTitle());
        movie.setGenre(movieDto.getGenre());
        movie.setSynopsis(movieDto.getSynopsis());
        movie.setImage(movieDto.getImage());
        movie.setFormat(movieDto.getFormat());
        movie.setPrice(movieDto.getPrice());
        return new ResponseEntity<>("Pelicula Actualizada", HttpStatus.OK);}

    }
        public ResponseEntity<List<Movie>> listByGenre(String genre) {
            if (!movieRepository.findAllByGenreContaining(genre).isEmpty()) {
                return new ResponseEntity<>(movieRepository.findAllByGenreContaining(genre), HttpStatus.OK);
            } else {
                return new ResponseEntity("no hay peliculas con ese genero", HttpStatus.NOT_FOUND);
            }
        }
}