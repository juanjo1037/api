package com.movies.api.service;

import com.movies.api.entity.Chair;
import com.movies.api.entity.Reservation;
import com.movies.api.entity.ReservedChair;
import com.movies.api.repository.ReservedChairRepository;
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
public class ReservedChairService {
    @Autowired
    ReservedChairRepository reservedChairRepository;

    public Optional<ReservedChair> findById(Long id) {
        return reservedChairRepository.findById(id);
    }

    public void save(ReservedChair reservedChair){reservedChairRepository.save(reservedChair);}

    public ResponseEntity<List<ReservedChair>> reserveChair(@NotNull List<Chair> chairs, Reservation reservation){

        List<ReservedChair> chairsReserved= new ArrayList<>();
    if (!chairs.isEmpty()){
        for(Chair chair: chairs){
            ReservedChair reservedChair= new ReservedChair(chair, reservation);
            reservedChairRepository.save(reservedChair);
            chairsReserved.add(reservedChair);
        }
        return new ResponseEntity<>(chairsReserved,HttpStatus.OK);
    }else
        return new ResponseEntity("error al reservar sillas",HttpStatus.BAD_REQUEST);
    }
        public void delete(Long id){
            reservedChairRepository.deleteById(id);
        }
}
