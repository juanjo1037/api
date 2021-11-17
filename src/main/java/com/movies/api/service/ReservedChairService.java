package com.movies.api.service;

import com.movies.api.entity.Chair;
import com.movies.api.entity.ReservedChair;
import com.movies.api.repository.ReservedChairRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservedChairService {
    ReservedChairRepository reservedChairRepository;

    public Optional<ReservedChair> findById(Long id) {
        return reservedChairRepository.findById(id);
    }

    public void save(ReservedChair reservedChair){reservedChairRepository.save(reservedChair);}
    public List<ReservedChair> reserveChair(List<Chair> chairs){

        List<ReservedChair> chairsReserved= new ArrayList<>();

        for(Chair chair: chairs){
            ReservedChair reservedChair= new ReservedChair(chair);
            reservedChairRepository.save(reservedChair);
            chairsReserved.add(reservedChair);
        }
        return chairsReserved;
    }
}
