package com.movies.api.repository;


import com.movies.api.entity.ReservedChair;
import com.movies.api.entity.ReservedChairId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ReservedChairRepository extends JpaRepository<ReservedChair, ReservedChairId> {

    @NotNull Optional<ReservedChair> findById(@NotNull ReservedChairId id);

    void deleteById(@NotNull ReservedChairId id);

    List<ReservedChair>findAllById_ReservationPresentationRoomIdAndId_ReservationPresentationScheduleAndId_ReservationUserId(Long idRoom, String schedule, Long userId);
    boolean existsById_ChairId (Long chairId);
    List<ReservedChair> findAllById_ReservationPresentationRoomIdAndId_ReservationPresentationSchedule(Long idRoom, String schedule);
    void deleteAllById_ReservationPresentationRoomIdAndId_ReservationPresentationScheduleAndId_ReservationUserId(Long roomId, String schedule, Long userId);

}
