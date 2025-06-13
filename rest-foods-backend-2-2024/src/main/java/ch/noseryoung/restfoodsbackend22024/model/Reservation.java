package ch.noseryoung.restfoodsbackend22024.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservation_id;

    private Long userId;

    private LocalDateTime reservation_date;

    private int amountPeople;

    private String username;

    private enum ReservationStatus {
        PENDING,
        CONFIRMED,
        CANCELLED
    }


}
