package com.movies.api.repository;

import com.movies.api.entity.Chair;
import com.movies.api.entity.Room;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChairRepository  extends JpaRepository<Chair,Long> {

    @Override
    @NotNull
    Optional<Chair> findById(@NotNull Long id);
    @Override
    boolean existsById(@NotNull Long id);

    Optional<Chair>findByRowAndColumnAndRoom(String row, int column, Room room);


    List<Chair> findAllByRoom(Room room);
}
