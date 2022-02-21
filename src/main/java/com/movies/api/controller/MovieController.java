package com.movies.api.controller;


import com.movies.api.dto.MovieDto;
import com.movies.api.entity.Movie;
import com.movies.api.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {

    @Autowired
    MovieService movieService;


    @Operation(summary = "Listar las peliculas que están en cartelera / List the movies that are on the billboard")
    @GetMapping
    public ResponseEntity<List<Movie>>listAllOnBillboard(){
        return new ResponseEntity(movieService.findMoviesByBillboard(), HttpStatus.OK);
    }

    @GetMapping("/coming_soon")
    public ResponseEntity<List<Movie>>listAllByComingSoon(){
            return new ResponseEntity(movieService.findMoviesByComingSoon(), HttpStatus.OK);
    }

    @Operation(summary = "Listar todas las peliculas")
    @GetMapping("/all")
    public ResponseEntity<List<Movie>>listAll(){
        return new ResponseEntity<>(movieService.findAll(),HttpStatus.OK);
    }

    @Operation(summary = "Obtener una pelicula por su id")
    @GetMapping("/{id}")
    public ResponseEntity<Movie>getById(@PathVariable("id")Long id){

        if(movieService.existById(id)){
            return new ResponseEntity(movieService.findById(id).get(), HttpStatus.OK);
        }else{


            return new ResponseEntity("no existe una pelicula con ese Id", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary="Obtener todas las peliculas de un genero")
    @GetMapping("/list")
    public ResponseEntity<List<Movie>> listByGenre(@RequestParam("genre") String genre){
        return movieService.listByGenre(genre);
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
        if(movieService.existById(id)){
            movieService.delete(id);
            return new ResponseEntity<>("pelicula eliminada", HttpStatus.OK);
        }else
            return new ResponseEntity("no se encontró la pelicula", HttpStatus.NOT_FOUND);


    }
}
