package com.movies.api.controller;


import com.movies.api.dto.Message;
import com.movies.api.dto.MovieDto;
import com.movies.api.entity.Movie;
import com.movies.api.entity.Room;
import com.movies.api.service.MovieService;
import com.movies.api.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {

    @Autowired
    MovieService movieService;


    RoomService roomService;

    @GetMapping("/list")
    public ResponseEntity<List<Movie>>listAll(){
        Iterable<Movie> list=movieService.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Movie>getById(@PathVariable("id")Long id){

        if(!movieService.existById(id))
            return new ResponseEntity(new Message("no existe una pelicula con ese Id"), HttpStatus.NOT_FOUND);
        Movie movie= movieService.findById(id).get();
        return new ResponseEntity(movie, HttpStatus.OK);
    }

    @GetMapping("/detail_title/{title}")
    public ResponseEntity<Movie>getByTitle(@PathVariable("title")String title){

        if(movieService.findByTitle(title)==null)
            return new ResponseEntity(new Message("no existe una pelicula con ese titulo"), HttpStatus.NOT_FOUND);
        Movie movie= (Movie) movieService.findByTitle(title);
        return new ResponseEntity(movie, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMovie(@RequestBody MovieDto movieDto){


        return movieService.createMovie(movieDto);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody MovieDto movieDto){
        Movie movie= movieService.findById(id).get();
        movie.setTitle(movieDto.getTitle());
        movie.setGenre(movieDto.getGenre());
        movie.setSynopsis(movieDto.getSynopsis());
        movie.setFormat(movieDto.getFormat());
        movie.setSchedule(movieDto.getSchedule());
        movie.setPrice(movieDto.getPrice());
        List<Room> rooms= roomService.findById(movieDto.getRoomId());
        movie.setRooms(rooms);
        return new ResponseEntity<>(new Message("Pelicula Actualizada"),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        if(!movieService.existById(id))
            return new ResponseEntity(new Message("no existe una reserva con ese Id"), HttpStatus.NOT_FOUND);
        movieService.delete(id);
        return new ResponseEntity<>(new Message("reserva eliminada"), HttpStatus.OK);

    }
}
