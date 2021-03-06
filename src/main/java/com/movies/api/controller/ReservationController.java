package com.movies.api.controller;

import com.movies.api.dto.EditReservationDto;
import com.movies.api.dto.Message;
import com.movies.api.dto.ReservationDto;
import com.movies.api.entity.Movie;
import com.movies.api.entity.Reservation;
import com.movies.api.service.MovieService;
import com.movies.api.service.UserService;
import com.movies.api.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    MovieService movieService;
    UserService userService;



    @GetMapping("/list")
    public ResponseEntity<List<Reservation>>listAll(){
        List<Reservation> list=reservationService.listAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Reservation>getById(@PathVariable("id")Long id){

        if(reservationService.existById(id))
            return new ResponseEntity(new Message("no existe una reserva con ese Id"), HttpStatus.NOT_FOUND);
        Reservation reservation= reservationService.getById(id).get();
        return new ResponseEntity(reservation, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDto reservationDto){

        Optional<Movie> optMovie= movieService.findByTitleAndFormat(reservationDto.getMovieTitle(),reservationDto.getMovieFormat());
        if (!optMovie.isPresent()){
            return new ResponseEntity("La pelicula no se encontrĂ³", HttpStatus.NOT_FOUND);
        }
        return  reservationService.createReservation(reservationDto, optMovie);



    }

    @DeleteMapping("/delete-chairs")
    public ResponseEntity<?> deleteChairs(@RequestBody EditReservationDto editReservationDto){

            return reservationService.deleteChairs(editReservationDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        if(!reservationService.existById(id))
            return new ResponseEntity("no existe una reserva con ese Id", HttpStatus.NOT_FOUND);
        reservationService.delete(id);
        return new ResponseEntity<>("reserva eliminada", HttpStatus.OK);

    }
}
