package com.movies.api.controller;


import com.movies.api.dto.Message;
import com.movies.api.dto.MovieDto;
import com.movies.api.entity.Movie;
import com.movies.api.entity.Room;
import com.movies.api.service.MovieService;
import com.movies.api.service.RoomService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    RoomService roomService;


    @Operation(summary = "Listar las peliculas que están en cartelera / List the movies that are on the billboard")
    @GetMapping
    public ResponseEntity<List<Movie>>listAll(){
        List<Movie> list=movieService.findMoviesByBillboard();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @Operation(summary = "Obtener una pelicula por su id")
    @GetMapping("/{id}")
    public ResponseEntity<Movie>getById(@PathVariable("id")Long id){

        if(movieService.existById(id))
            return new ResponseEntity(new Message("no existe una pelicula con ese Id"), HttpStatus.NOT_FOUND);
        Movie movie= movieService.findById(id).get();
        return new ResponseEntity(movie, HttpStatus.OK);
    }
    @Operation(summary = "Obtener una pelicula por su titulo/Get a movie by its title")
    @GetMapping("/{title}")
    public ResponseEntity<Movie> getByTitle(@PathVariable("title")String title){

        if(movieService.findByTitle(title)==null)
            return new ResponseEntity(new Message("no existe una pelicula con ese titulo"), HttpStatus.NOT_FOUND);
        Movie movie= (Movie) movieService.findByTitle(title);
        return new ResponseEntity(movie, HttpStatus.OK);
    }
    
    @Operation(summary = "crear una pelicula/ create  movie")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody MovieDto movieDto){

            return movieService.createMovie(movieDto);
    }

    @Operation(summary = "modificar una pelicula/update movie")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody MovieDto movieDto){

        return movieService.updateMovie(id, movieDto);
    }
    @Operation(summary = "eliminar una pelicula/delete movie")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        if(movieService.existById(id))
            return new ResponseEntity(new Message("no existe una reserva con ese Id"), HttpStatus.NOT_FOUND);
        movieService.delete(id);
        return new ResponseEntity<>(new Message("reserva eliminada"), HttpStatus.OK);

    }
}
