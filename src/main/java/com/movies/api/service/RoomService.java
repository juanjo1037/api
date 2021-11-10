package com.movies.api.service;

import com.movies.api.entity.Room;
import com.movies.api.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomService {

    @Autowired
    RoomRepository roomRepository;


    public List<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    public List<Room> findByCapacity(int capacity){

        return roomRepository.findByCapacity(capacity);
    }
}
