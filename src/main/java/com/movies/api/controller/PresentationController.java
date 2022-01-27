package com.movies.api.controller;


import com.movies.api.dto.PresentationDto;
import com.movies.api.entity.Chair;
import com.movies.api.entity.Presentation;
import com.movies.api.service.ChairService;
import com.movies.api.service.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presentation")
public class PresentationController {

    @Autowired
    PresentationService presentationService;
    @Autowired
    ChairService chairService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String>createPresentation(@RequestBody PresentationDto presentationDto){
        return presentationService.createPresentation(presentationDto);
    }

    @GetMapping
    public ResponseEntity<List<Presentation>> listAll(){

        return presentationService.listAll();

    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<String> deletePresentation(@RequestBody PresentationDto presentationDto){
        return presentationService.deletePresentation(presentationDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Presentation>>listByMovie(@PathVariable("id") Long id){
        if (presentationService.findAllByMovie(id)!=null){
            return new ResponseEntity<>(presentationService.findAllByMovie(id), HttpStatus.OK);

        }else
            return new ResponseEntity("No hay presentaciones para esta pelicula", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/chair/{idRoom}")
    public ResponseEntity<List<Chair>>listChairsByRoom(@PathVariable("idRoom")Long idRoom){

        return new ResponseEntity<>(chairService.findAllByRoom(idRoom), HttpStatus.ACCEPTED);
    }
}
