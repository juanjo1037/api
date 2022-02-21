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

    public  ResponseEntity<String> delete(ReservationDto reservationDto){
        if (userService.getByEmail(reservationDto.getEmail()).isPresent()){
            Long userId = userService.getByEmail(reservationDto.getEmail()).get().getId();
            ReservationId reservationId = new ReservationId(reservationDto.getIdRoom(),reservationDto.getSchedule(),userId);
        if (this.existById(reservationId)) {
            reservedChairService.deleteByReservation(reservationDto.getIdRoom(),reservationDto.getSchedule(),userId);
            reservationRepository.deleteById(reservationId);
            return new ResponseEntity<>("Reserva eliminada satisfactoriamente",HttpStatus.OK);
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



    public String createReservation(ReservationDto reservationDto ) {
        User user;
        List<Chair> chairs = new ArrayList<>();
        Optional<Chair> optionalChair;
        double price = 0;
        Chair chair;

        Optional<User> optionalUser = userService.getByEmail(reservationDto.getEmail());
        if (!optionalUser.isPresent()) {
            return "Usuario no encontrado";
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
                        return "Error al encontrar la silla";
                    }

                        chairs.add(chair);

            }
        }else
            return "no se pudo encontrar la sala";


        if (movieService.findById(reservationDto.getIdMovie()).isPresent()) {
            price = (movieService.findById(reservationDto.getIdMovie()).get().getPrice()) * reservationDto.getChairsNumber();
        }
        ReservationId reservationId= new ReservationId(reservationDto.getIdRoom(),reservationDto.getSchedule(),user.getId());
        Optional<Reservation> optionalReservation=reservationRepository.findById(reservationId);
        Reservation reservation;
        if(optionalReservation.isPresent()){
            reservation = optionalReservation.get();
            reservation.setChairsNumber(reservation.getChairsNumber()+reservationDto.getChairsNumber());
            reservation.setPrice(reservation.getPrice()+price);
        }else{
            reservation = new Reservation(
                    reservationId,
                    (reservationDto.getChairsNumber()),
                    price
            );
        }
        reservationRepository.save(reservation);
        reservedChairService.reserveChair(chairs, reservationDto.getIdRoom(), reservationDto.getSchedule(), user.getId());
        return "Reserva Creada Exitosamente";


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

    public String updateReservation(ReservationDto reservationDto ) {
        Long userId;
        List<Chair> chairs = new ArrayList<>();

        double price;



        userId=getUser(reservationDto.getEmail());
        Optional<Room>optionalRoom= roomService.findById(reservationDto.getIdRoom());
        if (optionalRoom.isPresent()){
            Room room= optionalRoom.get();
            for (int i = 0; i < reservationDto.getRows().size(); i++) {

                Optional<Chair> optionalChair = chairService.findByRowAndColumnAndRoom(reservationDto.getRows().get(i),
                        reservationDto.getColumns().get(i),room);
                if (optionalChair.isPresent()) {
                    Chair chair = optionalChair.get();

                        chairs.add(chair);


                } else {
                    return "No se encontró la silla";
                }

            }

        }else
            return "No se encontró la sala";
        price=getPrice(reservationDto.getIdMovie(), reservationDto.getChairsNumber());
        ReservationId reservationId= new ReservationId(reservationDto.getIdRoom(),reservationDto.getSchedule(),userId);
        Optional<Reservation> optionalReservation=reservationRepository.findById(reservationId);
        if(optionalReservation.isPresent()){
            Reservation reservation= optionalReservation.get();
            reservation.setChairsNumber(reservationDto.getChairsNumber());
            reservation.setPrice(price);
            reservedChairService.deleteByReservation(reservationDto.getIdRoom(),reservationDto.getSchedule(),userId);

            reservationRepository.save(reservation);
            reservedChairService.reserveChair(chairs, reservationDto.getIdRoom(), reservationDto.getSchedule(), userId);
        }else
            return "No se pudo encontrar la reserva";


        return "Reserva Actualizada Exitosamente";


    }

    public Long getUser(String email){
        Optional<User> optionalUser = userService.getByEmail(email);
        User user;
        if (!optionalUser.isPresent()) {
            return null;
        } else {
            user = optionalUser.get();
            return user.getId();
        }
    }
    public double getPrice(Long idMovie, int chairsNumber){
        Optional<Movie>optionalMovie= movieService.findById(idMovie);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            return movie.getPrice() * chairsNumber;
        }
        return 0;
    }

}
