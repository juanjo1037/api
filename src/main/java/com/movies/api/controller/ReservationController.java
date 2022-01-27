package com.movies.api.controller;

import com.movies.api.dto.EditReservationDto;
import com.movies.api.dto.Message;
import com.movies.api.dto.ReservationDto;
import com.movies.api.entity.Reservation;
import com.movies.api.entity.ReservationId;
import com.movies.api.entity.ReservedChair;
import com.movies.api.service.MovieService;
import com.movies.api.service.ReservedChairService;
import com.movies.api.service.UserService;
import com.movies.api.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservedChairService reservedChairService;



    @Operation(summary = "listar las reservas de un usuario/ list a user's reservations")
    @GetMapping
    public ResponseEntity<List<Reservation>>listAllByUser(@RequestParam("email") String email){

        return reservationService.listAllByUser(email);
    }

//    @Operation(summary = "obtener una reserva por su id/ get a reservation by your id")
//    @GetMapping("/{id}")
//    public ResponseEntity<Reservation>getById(@PathVariable("id")Long id){
//
//        if(reservationService.existById(id))
//            return new ResponseEntity(new Message("no existe una reserva con ese Id"), HttpStatus.NOT_FOUND);
//        Reservation reservation= reservationService.getById(id).get();
//        return new ResponseEntity(reservation, HttpStatus.OK);
//    }

    @Operation(summary = "crear reserva/create reservation")
    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationDto reservationDto){


        return  reservationService.createReservation(reservationDto);



    }
    @Operation(summary = "eliminar sillas que pertenecen a una reserva / remove chairs that belong to a reserve")
    @DeleteMapping("/chair")
    public ResponseEntity<?> deleteChairs(@RequestBody EditReservationDto editReservationDto){

            return reservationService.deleteChairs(editReservationDto);
    }

    @Operation(summary = "eliminar una reserva / remove reservation")
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody ReservationDto reservationDto){

            return reservationService.delete(reservationDto);


    }
    @GetMapping("/chairs/{idRoom}")
    public ResponseEntity<List<ReservedChair>> getReservedChairs(@PathVariable("idRoom") Long idRoom){
        return new ResponseEntity(reservedChairService.findByIdRoom(idRoom), HttpStatus.ACCEPTED);
    }
}
