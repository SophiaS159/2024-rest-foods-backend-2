package ch.noseryoung.restfoodsbackend22024.DTO;

import ch.noseryoung.restfoodsbackend22024.model.Reservation;
import ch.noseryoung.restfoodsbackend22024.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@Setter
public class ReservationDTO {

    private Long reservationId;
    private String formattedDate;
    private String formattedTime;
    private int amountPeople;
    private String reservationStatus;
    private String userLogin;

    private static final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("d. MMM. yyyy", Locale.GERMAN);
    private static final DateTimeFormatter TIMEFORMATTER = DateTimeFormatter.ofPattern("HH:mm", Locale.GERMANY);

    public ReservationDTO(Reservation reservation) {
        this.reservationId = reservation.getReservationId();
        if (reservation.getReservationDate() != null) {
            this.formattedDate = reservation.getReservationDate().format(DATEFORMATTER);
        } else {
            this.formattedDate = "kein Datum";
        }
        if (reservation.getReservationTime() != null) {
            this.formattedTime = reservation.getReservationTime().format(TIMEFORMATTER);
        } else {
            this.formattedTime = "-";
        }
        this.amountPeople = reservation.getAmountPeople();
        this.reservationStatus = reservation.getReservationStatus() != null ? reservation.getReservationStatus().name() : "UNBEKANNT";
        this.userLogin = reservation.getUser() != null ? reservation.getUser().getLogin() : "Unbekannt";
    }

}