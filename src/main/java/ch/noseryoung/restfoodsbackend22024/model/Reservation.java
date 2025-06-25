package ch.noseryoung.restfoodsbackend22024.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("reservationDate")
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private int amountPeople;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    public enum ReservationStatus {
        PENDING,
        ACCEPTED,
        DECLINED,
    }

}
