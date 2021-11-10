package com.movies.api.service;

import com.movies.api.entity.Movie;
import com.movies.api.entity.Reservation;
import com.movies.api.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationService {

        @Autowired
        ReservationRepository reservationRepository;



        public List<Reservation> listAll(){

            return reservationRepository.findAll();
        }


        public Optional<Reservation> getById(Long id){
            return reservationRepository.findById(id);
        }


        public void save(Reservation reservation){
            reservationRepository.save(reservation);
    }




        public  void delete(Long id){
            reservationRepository.deleteById(id);
        }

        public boolean existById(Long id){
                return reservationRepository.existsById(id);
        }

}
