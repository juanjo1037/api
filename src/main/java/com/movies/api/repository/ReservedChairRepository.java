package com.movies.api.repository;


import com.movies.api.entity.ReservedChair;
import com.movies.api.entity.ReservedChairId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ReservedChairRepository extends JpaRepository<ReservedChair, Long> {

    Optional<ReservedChair> findById(ReservedChairId id);

    void deleteById(ReservedChairId id);

    boolean existsByIdChairIdAndId_ReservationPresentationRoomIdAndId_ReservationPresentationSchedule(Long idChair, Long idRoom, String schedule);

    boolean existsById_ChairId (Long chairId);
    List<ReservedChair> findAllById_ReservationPresentationRoomId(Long idRoom);
    void deleteAllById_ReservationPresentationRoomIdAndId_ReservationPresentationScheduleAndId_ReservationUserId(Long roomId, String schedule, Long userId);
}
