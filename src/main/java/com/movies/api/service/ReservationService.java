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

    public Optional<Reservation> getById(Long id){
        return reservationRepository.findById(id);
    }

    public  void delete(Long id){
        reservationRepository.deleteById(id);
    }

    public boolean existById(Long id){
        return reservationRepository.existsById(id);
    }




    public ResponseEntity<List<Reservation>> listAllByUser(String email){
          if (userService.getByEmail(email).isPresent()) {
              User user = userService.getByEmail(email).get();
              return new ResponseEntity<>(reservationRepository.findAllByUser(user), HttpStatus.OK);
          }
            return new ResponseEntity("El usuario no existe o no tiene reservas", HttpStatus.NOT_FOUND);
        }



    public ResponseEntity<String> createReservation(@NotNull ReservationDto reservationDto ) {
        User user;
        ReservedChairService reservedChairService = new ReservedChairService();
        List<Chair> chairs = new ArrayList<>();
        Optional<Chair> optionalChair;
        float price = 0;
        PresentationId presentationId = new PresentationId(reservationDto.getIdMovie(), reservationDto.getIdRoom(), reservationDto.getSchedule());
        Presentation presentation;
        Chair chair;
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

        for (int i = 0; i < reservationDto.getChairsNumber(); i++) {

            optionalChair = chairService.findByRowAndColumn(reservationDto.getRows().get(i),
                    reservationDto.getColumns().get(i));
            if (optionalChair.isPresent()) {
                chair = optionalChair.get();
            } else {
                return new ResponseEntity<>("Error al encontrar la silla", HttpStatus.NOT_FOUND);
            }
            if (!chair.isReserved()) {

                chairs.add(chair);

            } else
                return new ResponseEntity<>("La silla ya se encuentra reservada", HttpStatus.BAD_REQUEST);
        }

        if (movieService.findById(reservationDto.getIdMovie()).isPresent()) {
            price = (movieService.findById(reservationDto.getIdMovie()).get().getPrice()) * reservationDto.getChairsNumber();
        }


            Reservation reservation = new Reservation(
                    reservationDto.getChairsNumber(),
                    price,
                    user,
                    presentation
                    );
            user.addReservations(reservation);
            reservation.setReservedChairs(reservedChairService.reserveChair(chairs,reservation).getBody());
            reservationRepository.save(reservation);
            return new ResponseEntity<>("Reserva Creada", HttpStatus.CREATED);

    }

    public ResponseEntity<String>deleteChairs(EditReservationDto editReservationDto){
            if (!editReservationDto.getChairsToDelete().isEmpty()){
            for ( int i=0 ; i<editReservationDto.getChairsToDelete().size(); i++ ){
               Long index= editReservationDto.getChairsToDelete().get(i);

                if (reservedChairService.findById(index).isPresent()){
                    reservedChairService.findById(index).get().getReservation()
                            .setChairsNumber(
                                    reservedChairService.findById(index).get().getReservation().getChairsNumber()
                                    -editReservationDto.getChairsToDelete().size()
                            );
                 reservedChairService.delete(reservedChairService.findById(index).get().getId());

                }
            }
            return new ResponseEntity<>("La reserva se ha actualizado con éxito",HttpStatus.OK);
            }else
                return new ResponseEntity<>("debe seleccionar una silla reservada",HttpStatus.BAD_REQUEST);
    }
}
