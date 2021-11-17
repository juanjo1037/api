package com.movies.api.service;


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
        MovieService movieService;
        @Autowired
        ChairService chairService;


        public List<Reservation> listAll(){

            return reservationRepository.findAll();
        }


        public Optional<Reservation> getById(Long id){
            return reservationRepository.findById(id);
        }

        public  void delete(Long id){
            reservationRepository.deleteById(id);
        }

        public boolean existById(Long id){
                return !reservationRepository.existsById(id);
        }

    public ResponseEntity<String> createReservation(@NotNull ReservationDto reservationDto,
                                                    @NotNull Optional<Movie> optionalMovie) {
            ReservedChairService reservedChairService= new ReservedChairService();
            List<Chair>chairs= new ArrayList<>();
            Movie movie= new Movie();
            int row, column;
            Optional<Chair> optionalChair;
            for(int i=0; i<reservationDto.getChairsNumber(); i++){
                row= reservationDto.getRows().get(i);
                column= reservationDto.getColumns().get(i);

               optionalChair = chairService.findByRowAndColumn(row,column);
               if (!optionalChair.isPresent() || optionalChair.get().isReserved()){
                   return new ResponseEntity<>("La silla no se encontró o está reservada", HttpStatus.BAD_REQUEST);
               }else{
                optionalChair.ifPresent(chairs::add);
               optionalChair.get().setReserved(true);
               }
            }
            if (optionalMovie.isPresent()){
                movie=optionalMovie.get();
            }

        Reservation reservation= new Reservation(
                reservationDto.getChairsNumber(),
                reservationDto.getPrice(),
                reservationDto.getUser(),
                movie,
                reservedChairService.reserveChair(chairs)
        );
            reservationRepository.save(reservation);

            return new ResponseEntity<>("Reserva Creada", HttpStatus.CREATED);
    }

}
