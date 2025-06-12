package ch.noseryoung.restfoodsbackend22024.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Column(nullable = false)
    @NotNull
    private Long user_id;

    @Column(nullable = false)
    @FutureOrPresent(message = "Das Reservierungsdatum muss in der Gegenwart oder Zukunft liegen.")
    @NotNull
    private LocalDateTime reservation_date;

    @Column(nullable = false)
    @Min(value = 1, message = "Mindestens 1 Person muss reservieren.")
    private int amountPeople;

    @Column(length = 100, nullable = false)
    @Size(max = 100)
    @NotBlank
    private String user_name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReservationStatus status = ReservationStatus.PENDING;

    public enum ReservationStatus {
        PENDING,
        CONFIRMED,
        CANCELLED
    }
}
