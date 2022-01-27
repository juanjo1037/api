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


    public List<Presentation>findAllByMovie(Long id){

       if (movieService.findById(id).isPresent()){
            Movie movie = movieService.findById(id).get();
            return presentationRepository.findAllByMovie(movie);
        }
       else
           return null;

    }
    public List<Presentation>findAllByRoom(Long room){
        return  presentationRepository.findAllById_RoomId(room);
    }


   public ResponseEntity<String>createPresentation(PresentationDto presentationDto){
       Movie movie;
        PresentationId presentationId= new PresentationId(
                presentationDto.getIdRoom(),
                presentationDto.getSchedule()
        );

        if (movieService.findById(presentationDto.getIdMovie()).isPresent()){
            movie=movieService.findById(presentationDto.getIdMovie()).get();
        }else {
            return new ResponseEntity<>("No se encontró una pelicula con ese id", HttpStatus.NOT_FOUND);
        }
        Optional<Room>optionalRoom= roomService.findById(presentationDto.getIdRoom());
        if (!optionalRoom.isPresent()){
            return new ResponseEntity<>("No se encontró una sala con ese id", HttpStatus.NOT_FOUND);
        }
       Presentation presentation= new Presentation(presentationId,movie);
        if (!presentationRepository.existsByIdAndMovie(presentationId, movie)){
        presentationRepository.save(presentation);
        return new ResponseEntity<>("Presentación creada", HttpStatus.CREATED);
        }else
            return new ResponseEntity<>("Ya existe una presentación para esa pelicula en esa sala y en ese horario", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deletePresentation (PresentationDto presentationDto){
            PresentationId presentationId= new PresentationId(presentationDto.getIdRoom(), presentationDto.getSchedule());
            if(presentationRepository.existsById(presentationId)){
                presentationRepository.deleteById(presentationId);
                return new ResponseEntity<>("Presentación Eliminada", HttpStatus.OK);
            }else
                return new ResponseEntity<>("No hay una presentación con ese ID", HttpStatus.NOT_FOUND);

    }
}
