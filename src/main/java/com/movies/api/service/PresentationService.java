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
    @Autowired
    ReservedChairService reservedChairService;

   public Optional<Presentation> findById(PresentationId id){
        return presentationRepository.findByIdAndAvailable(id, true);
    }

    public ResponseEntity<List<Presentation>> listAll(){

        return new ResponseEntity<>(presentationRepository.findAll(), HttpStatus.OK);
    }

   public List<Presentation> findAllbyId(PresentationId id){
        return presentationRepository.findAllByIdAndAvailable(id, true);
    }


    public List<Presentation>findAllByMovie(Long id){

       if (movieService.findById(id).isPresent()){
            Movie movie = movieService.findById(id).get();
            return presentationRepository.findAllByMovieAndAvailable(movie,true);
        }
       else
           return null;

    }
    public boolean existsByMovie(Movie movie){
       return presentationRepository.existsByMovieAndAvailable(movie, true);
    }
    public List<Presentation>findAllByRoom(Long room){
        return  presentationRepository.findAllById_RoomIdAndAvailable(room, true);
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
       Presentation presentation= new Presentation(presentationId,movie,presentationDto.isAvailable());
        if (!presentationRepository.existsById(presentationId)){
        presentationRepository.save(presentation);
        return new ResponseEntity<>("Presentación creada", HttpStatus.CREATED);
        }else
            return new ResponseEntity<>("Ya existe una presentación en esa sala y en ese horario", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deletePresentation (PresentationDto presentationDto){
            PresentationId presentationId= new PresentationId(presentationDto.getIdRoom(), presentationDto.getSchedule());
            if(presentationRepository.existsById(presentationId)){
                presentationRepository.deleteById(presentationId);
                return new ResponseEntity<>("Presentación Eliminada", HttpStatus.OK);
            }else
                return new ResponseEntity<>("No hay una presentación con ese ID", HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<String> updatePresentation(PresentationDto presentationDto) {
        Movie movie;
        PresentationId presentationId= new PresentationId(
                presentationDto.getIdRoom(),
                presentationDto.getSchedule()
        );
        if (movieService.findById(presentationDto.getIdMovie()).isPresent()){
            movie=movieService.findById(presentationDto.getIdMovie()).get();
            if(movie.isComingSoon()){
                return new ResponseEntity("Esta pelicula aun no está en cartelera, debes actualizarla",HttpStatus.FORBIDDEN);
            }
        }else {
            return new ResponseEntity("No se encontró una pelicula con ese id", HttpStatus.NOT_FOUND);
        }
        Optional<Presentation>optionalPresentation=presentationRepository.findByIdAndAvailable(presentationId,true);
        if (optionalPresentation.isPresent()){
            if(!reservedChairService.existsByPresentation(presentationId.getRoomId(), presentationId.getSchedule())){
                Presentation presentation = optionalPresentation.get();
                presentation.setMovie(movie);
                presentation.setAvailable(presentationDto.isAvailable());
                presentationRepository.save(presentation);
                return new ResponseEntity("Presentación Actualizada", HttpStatus.OK);
            }else
                return  new ResponseEntity<>("Ya existen reservas para esa pelicula, no se puede actualizar la presentación", HttpStatus.BAD_REQUEST);
        }else
            return new ResponseEntity("No se pudo encontrar la presentación", HttpStatus.BAD_REQUEST);
    }
}
