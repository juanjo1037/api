package com.movies.api.service;


import com.movies.api.entity.Chair;
import com.movies.api.entity.Room;
import com.movies.api.repository.ChairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChairService {

    @Autowired
    ChairRepository chairRepository;

    @Autowired
    RoomService roomService;

    public ChairService(ChairRepository chairRepository) {
        this.chairRepository = chairRepository;
    }

    public Optional<Chair> findById(Long id) {
        return chairRepository.findById(id);
    }

    public Optional<Chair> findByRowAndColumnAndRoom(String row, int column, Room room) {
        return chairRepository.findByRowAndColumnAndRoom(row,column, room);
    }

    public List<Chair> findAllByRoom(Long idRoom){
     if   (roomService.findById(idRoom).isPresent()){
         Room room =roomService.findById(idRoom).get();
         return chairRepository.findAllByRoom(room);
     }
     return new ArrayList<>();
    }

    public void createChairs(int rowsNumber, int columnsNumber,Room room){

        for (int i=0; i<rowsNumber;i++){
            char row= ((char)(i+65));
            for(int j=0; j<columnsNumber; j++){
                // convertimos el numero a letra con ASCII
                Chair chair= new Chair(Character.toString(row),j+1,room);
                chairRepository.save(chair);
            }
        }

    }

}
