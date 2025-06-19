package ch.noseryoung.restfoodsbackend22024.repository;

import ch.noseryoung.restfoodsbackend22024.model.Reservation;
import ch.noseryoung.restfoodsbackend22024.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
}
