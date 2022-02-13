package com.movies.api.controller;

import com.movies.api.dto.EditReservationDto;
import com.movies.api.dto.ReservationDto;
import com.movies.api.entity.Reservation;
import com.movies.api.entity.ReservedChair;
import com.movies.api.service.ReservationService;
import com.movies.api.service.ReservedChairService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @Operation(summary = "crear reserva/create reservation")
    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationDto reservationDto){
        String response= reservationService.createReservation(reservationDto);
    if (Objects.equals(response, "Reserva Creada Exitosamente")){
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }else
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);




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
    public ResponseEntity<List<ReservedChair>> getReservedChairs(@PathVariable("idRoom") Long idRoom,
                                                                 @RequestParam("schedule") String schedule){
        return new ResponseEntity(reservedChairService.findByIdRoomAndSchedule(idRoom, schedule), HttpStatus.ACCEPTED);
    }

    @GetMapping("/reserved_chairs")
    public ResponseEntity<List<ReservedChair>>getReservedChairsByUser(@RequestParam("roomId") String roomId,@RequestParam("schedule") String schedule, @RequestParam("userId") String userId ){
    Long idRoom=Long.parseLong(roomId);
    Long idUser=Long.parseLong(userId);
        System.out.println(idRoom);
        return new ResponseEntity<>(reservedChairService.findByUserAndPresentation(idRoom,schedule,idUser),HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<String>updateReservation(@RequestBody ReservationDto reservationDto){
        return  new ResponseEntity<>(reservationService.updateReservation(reservationDto), HttpStatus.ACCEPTED);
    }
}
