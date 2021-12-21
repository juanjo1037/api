package com.movies.api.service;

import com.movies.api.dto.PresentationDto;
import com.movies.api.entity.Movie;
import com.movies.api.entity.Presentation;
import com.movies.api.entity.PresentationId;
import com.movies.api.entity.Room;
import com.movies.api.repository.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PresentationService {
    @Autowired
    PresentationRepository presentationRepository;
    @Autowired
    MovieService movieService;
    @Autowired
    RoomService roomService;

   public Optional<Presentation> findById(PresentationId id){
        return presentationRepository.findById(id);
    }

    public ResponseEntity<List<Presentation>> listAll(){

        return new ResponseEntity<>(presentationRepository.findAll(), HttpStatus.OK);
    }

   public List<Presentation> findAllbyId(PresentationId id){
        return presentationRepository.findAllById(id);
    }
    public List<Presentation>findAllByMovie(Movie movie){
        return presentationRepository.findAllByMovie(movie);
    }
    public List<Presentation>findAllByRoom(Room room){
        return  presentationRepository.findAllByRoom(room);
    }


   public ResponseEntity<String>createPresentation(PresentationDto presentationDto){
       Movie movie;
       Room room;
        PresentationId presentationId= new PresentationId(presentationDto.getIdMovie(),
                presentationDto.getIdRoom(),
                presentationDto.getSchedule()
        );

        if (movieService.findById(presentationDto.getIdMovie()).isPresent()){
            movie=movieService.findById(presentationDto.getIdMovie()).get();
        }else {
            return new ResponseEntity<>("No se encontró una pelicula con ese id", HttpStatus.NOT_FOUND);
        }
        Optional<Room>optionalRoom= roomService.findById(presentationDto.getIdRoom());
        if (optionalRoom.isPresent()){
            room=optionalRoom.get();
        }else {
            return new ResponseEntity<>("No se encontró una sala con ese id", HttpStatus.NOT_FOUND);
        }
       Presentation presentation= new Presentation(presentationId,movie, room);
        presentationRepository.save(presentation);
        return new ResponseEntity<>("Presentación creada", HttpStatus.CREATED);
    }
}
