package com.movies.api.controller;


import com.movies.api.dto.PresentationDto;
import com.movies.api.entity.Presentation;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String>createPresentation(@RequestBody PresentationDto presentationDto){
        return presentationService.createPresentation(presentationDto);
    }

    @GetMapping
    public ResponseEntity<List<Presentation>> listAll(){

        return presentationService.listAll();

    }
}
