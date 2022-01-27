package com.movies.api.service;


import com.movies.api.dto.EditReservationDto;
import com.movies.api.dto.ReservationDto;
import com.movies.api.entity.*;
import com.movies.api.repository.ReservationRepository;
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
public class ReservationService {

        @Autowired
        ReservationRepository reservationRepository;
        @Autowired
        ChairService chairService;
        @Autowired
        ReservedChairService reservedChairService;
        @Autowired
        UserService userService;
        @Autowired
         MovieService movieService;
        @Autowired
        PresentationService presentationService;
        @Autowired
        RoomService roomService;

    public Optional<Reservation> getById(ReservationId id){
        return reservationRepository.findById(id);
    }

    public  ResponseEntity<String> delete(ReservationDto reservationDto){
        if (userService.getByEmail(reservationDto.getEmail()).isPresent()){
            Long userId = userService.getByEmail(reservationDto.getEmail()).get().getId();
            ReservationId reservationId = new ReservationId(reservationDto.getIdRoom(),reservationDto.getSchedule(),userId);
        if (this.existById(reservationId)) {
            reservedChairService.deleteByReservation(reservationDto.getIdRoom(),reservationDto.getSchedule(),userId);
            reservationRepository.deleteById(reservationId);
            return new ResponseEntity<>("Se eliminó la reserva",HttpStatus.OK);
        }else
            return new ResponseEntity<>("no hay una reserva con esos datos", HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity<>("no se encontró al usuario", HttpStatus.NOT_FOUND);
    }

    public boolean existById(ReservationId id){
        return reservationRepository.existsById(id);
    }




    public ResponseEntity<List<Reservation>> listAllByUser(String email){
        User user;
          if (userService.getByEmail(email).isPresent()) {
              user =userService.getByEmail(email).get();
              return new ResponseEntity<>(reservationRepository.findAllById_UserId(user.getId()), HttpStatus.OK);
          }
            return new ResponseEntity("El usuario no existe o no tiene reservas", HttpStatus.NOT_FOUND);
        }



    public ResponseEntity<String> createReservation(@NotNull ReservationDto reservationDto ) {
        User user;
        List<Chair> chairs = new ArrayList<>();
        Optional<Chair> optionalChair;
        Double price = null;
        Chair chair;
        PresentationId presentationId = new PresentationId(reservationDto.getIdRoom(), reservationDto.getSchedule());
        Presentation presentation;
        Optional<Presentation> optPresentation = presentationService.findById(presentationId);

        if (!optPresentation.isPresent()) {
            return new ResponseEntity("No se encontró una presentación", HttpStatus.NOT_FOUND);
        } else {
            presentation = optPresentation.get();
        }

        Optional<User> optionalUser = userService.getByEmail(reservationDto.getEmail());
        if (!optionalUser.isPresent()) {
            return new ResponseEntity("Usuario no encontrado", HttpStatus.NOT_FOUND);
        } else {
            user = optionalUser.get();
        }
        if (roomService.findById(reservationDto.getIdRoom()).isPresent()){
            Room room= roomService.findById(reservationDto.getIdRoom()).get();
                for (int i = 0; i < reservationDto.getChairsNumber(); i++) {

                    optionalChair = chairService.findByRowAndColumnAndRoom(reservationDto.getRows().get(i),
                            reservationDto.getColumns().get(i),room);
                    if (optionalChair.isPresent()) {
                        chair = optionalChair.get();
                    } else {
                        return new ResponseEntity<>("Error al encontrar la silla", HttpStatus.NOT_FOUND);
                    }
                    if (!reservedChairService.existsByChairId(chair.getId())) {
                        chairs.add(chair);
                    } else
                        return new ResponseEntity<>("La silla ya se encuentra reservada", HttpStatus.BAD_REQUEST);
            }
        }else
            return new ResponseEntity<>("no se pudo encontrar la sala", HttpStatus.NOT_FOUND);


        if (movieService.findById(reservationDto.getIdMovie()).isPresent()) {
            price = (movieService.findById(reservationDto.getIdMovie()).get().getPrice()) * reservationDto.getChairsNumber();
        }
        ReservationId reservationId= new ReservationId(reservationDto.getIdRoom(),reservationDto.getSchedule(),user.getId());
            Reservation reservation = new Reservation(
                    reservationId,
                    reservationDto.getChairsNumber(),
                    price
                    );
            reservationRepository.save(reservation);
            reservedChairService.reserveChair(chairs,reservationDto.getIdRoom(),reservationDto.getSchedule(),user.getId());
            return new ResponseEntity<>("Reserva Creada", HttpStatus.CREATED);

    }

    public ResponseEntity<String>deleteChairs(@NotNull EditReservationDto editReservationDto){
            if (!editReservationDto.getChairsId().isEmpty()){
                if (userService.getByEmail(editReservationDto.getEmail()).isPresent()){
                Long userId = userService.getByEmail(editReservationDto.getEmail()).get().getId();
            for ( int i=0 ; i<editReservationDto.getChairsId().size(); i++ ){
               ReservedChairId reservedChairId= new ReservedChairId(editReservationDto.getChairsId().get(i),
                       editReservationDto.getRoomId(),editReservationDto.getSchedule(),userId);

                if (reservedChairService.findById(reservedChairId).isPresent()){

                 reservedChairService.delete(reservedChairService.findById(reservedChairId).get().getId());

                }
            }
            return new ResponseEntity<>("La reserva se ha actualizado con éxito",HttpStatus.OK);
                }else
                    return  new ResponseEntity<>("no se encontró al usuario", HttpStatus.NOT_FOUND);
            }else
                return new ResponseEntity<>("debe seleccionar una silla reservada",HttpStatus.BAD_REQUEST);
    }
}
