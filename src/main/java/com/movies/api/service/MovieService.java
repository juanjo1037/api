package com.movies.api.service;


import com.movies.api.dto.MovieDto;
import com.movies.api.entity.Movie;
import com.movies.api.repository.MovieRepository;
import com.movies.api.repository.PresentationRepository;
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
    @Autowired
    PresentationService presentationService;

    public Optional<Movie> findById(Long id){
        return movieRepository.findByIdAndDeleted(id, false);
    }



    public boolean existById(Long id) {
        return movieRepository.existsById(id);
    }



    public void delete(Long id)
    {
        Optional<Movie>optionalMovie=movieRepository.findById(id);
        if(optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movie.setDeleted(true);
        }

    }
    public List<Movie>findMoviesByBillboard(){
            List<Movie> movies = movieRepository.findMoviesByBillboardAndDeleted(true,false);
        Collections.shuffle(movies);
        return movies;
        }
    public List<Movie>findMoviesByComingSoon(){
        List<Movie> movies = movieRepository.findMoviesByComingSoonAndDeleted(true,false);
        Collections.shuffle(movies);
        return movies;
    }

    public List<Movie>findAll(){
        return movieRepository.findAllByDeleted(false);
    }

    public Optional<Movie>findByTitleAndFormat(String title, String format){
        return movieRepository.findByTitleAndFormatAndDeleted(title,format,false);
    }
    public boolean existsByTitleAndFormat(String title, String format){
        return movieRepository.existsByTitleAndFormatAndDeleted(title, format, false);
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
                    movieDto.getBackDropImg(), movieDto.isComingSoon());


            movieRepository.save(movie);
            return new ResponseEntity<>("Pelicula creada con exito", HttpStatus.CREATED);
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
        movie.setDuration(movieDto.getDuration());
        movie.setSynopsis(movieDto.getSynopsis());
        movie.setImage(movieDto.getImage());
        movie.setFormat(movieDto.getFormat());
        movie.setBillboard(movieDto.isBillboard());
        movie.setPrice(movieDto.getPrice());
        movie.setBackDropImg(movieDto.getBackDropImg());
        movie.setComingSoon(movieDto.isComingSoon());
        return new ResponseEntity<>("Pelicula Actualizada", HttpStatus.OK);}

    }
        public ResponseEntity<List<Movie>> listByGenre(String genre) {
            if (!movieRepository.findAllByGenreContainingAndDeleted(genre,false).isEmpty()) {
                return new ResponseEntity<>(movieRepository.findAllByGenreContainingAndDeleted(genre,false), HttpStatus.OK);
            } else {
                return new ResponseEntity("no hay peliculas con ese genero", HttpStatus.NOT_FOUND);
            }
        }
}