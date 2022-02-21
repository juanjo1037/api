package com.movies.api.service;

import com.movies.api.dto.RoomDto;
import com.movies.api.entity.Chair;
import com.movies.api.entity.Room;
import com.movies.api.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomService {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ChairService chairService;

    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    public String createRoom(RoomDto roomDto){
            Room room = new Room((roomDto.getColumnsNumber()*roomDto.getRowsNumber()),roomDto.getRowsNumber(), roomDto.getColumnsNumber());
            roomRepository.save(room);
            chairService.createChairs(roomDto.getRowsNumber(),roomDto.getColumnsNumber(),room);
    return "Sala creada satisfactoriamente";
}
    public List<Room>getAllRooms(){
        return roomRepository.findAll();
    }
}