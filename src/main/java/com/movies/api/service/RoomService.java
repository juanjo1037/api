package com.movies.api.service;

import com.movies.api.entity.Room;
import com.movies.api.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomService {

    @Autowired
    RoomRepository roomRepository;


    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }
public boolean existsById(Long id){
        return roomRepository.existsById(id);
}
    public List<Room> findByCapacity(int capacity){

        return roomRepository.findByCapacity(capacity);
    }
}
