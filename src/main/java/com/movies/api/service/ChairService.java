package com.movies.api.service;


import com.movies.api.entity.Chair;
import com.movies.api.entity.Movie;
import com.movies.api.repository.ChairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Optional;

@Service
public class ChairService {


    final ChairRepository chairRepository;

    public ChairService(ChairRepository chairRepository) {
        this.chairRepository = chairRepository;
    }

    public Optional<Chair> findById(Long id) {
        return chairRepository.findById(id);
    }

    public boolean existById(Long id) {
        return chairRepository.existsById(id);
    }

    public Optional<Chair> findByRowAndColumn(int row, int column) {
        return chairRepository.findByRowAndColumn(row,column);
    }
    public void delete(Chair chair){
        chairRepository.delete(chair);
    }

}
