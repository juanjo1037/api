package com.movies.api.service;

import com.movies.api.entity.Chair;
import com.movies.api.entity.ReservedChair;
import com.movies.api.entity.ReservedChairId;
import com.movies.api.repository.ReservedChairRepository;
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

    public List<ReservedChair> findByIdRoom(Long idRoom){
        return reservedChairRepository.findAllById_ReservationPresentationRoomId(idRoom);

    }
    public Optional<ReservedChair> findById(ReservedChairId id) {
        return reservedChairRepository.findById(id);
    }

    public boolean existsByChairId(Long chairId){
        return reservedChairRepository.existsById_ChairId(chairId);
    }

    public ResponseEntity reserveChair(List<Chair> chairs, Long idRoom, String schedule, Long id ){

        List<ReservedChair> chairsReserved= new ArrayList<>();

        for(Chair chair: chairs){
            ReservedChairId reservedChairId = new ReservedChairId(chair.getId(), idRoom,schedule,id);
            ReservedChair reservedChair= new ReservedChair(reservedChairId);
            if (!reservedChairRepository.existsByIdChairIdAndId_ReservationPresentationRoomIdAndId_ReservationPresentationSchedule(chair.getId(), idRoom,schedule)){
            chairsReserved.add(reservedChair);
            }else
                return  new ResponseEntity("La silla ya está reservada", HttpStatus.BAD_REQUEST);
        }

        reservedChairRepository.saveAll(chairsReserved);
        return new ResponseEntity<>(chairsReserved,HttpStatus.OK);
    }


        public void delete(ReservedChairId id){
            reservedChairRepository.deleteById(id);
        }

        public void deleteByReservation(Long idRoom, String schedule, Long userId){
            reservedChairRepository.deleteAllById_ReservationPresentationRoomIdAndId_ReservationPresentationScheduleAndId_ReservationUserId(idRoom,schedule,userId);
        }

}
