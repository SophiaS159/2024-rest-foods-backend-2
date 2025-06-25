package ch.noseryoung.restfoodsbackend22024.controller;

import ch.noseryoung.restfoodsbackend22024.DTO.ReservationDTO;
import ch.noseryoung.restfoodsbackend22024.model.Reservation;
import ch.noseryoung.restfoodsbackend22024.model.User;
import ch.noseryoung.restfoodsbackend22024.repository.ReservationRepository;
import ch.noseryoung.restfoodsbackend22024.repository.UserRepository;
import ch.noseryoung.restfoodsbackend22024.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String login = authentication.getName();
        Optional<User> userOpt = userRepository.findByLogin(login);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        reservation.setUser(userOpt.get());

        if (reservation.getReservationStatus() == null) {
            reservation.setReservationStatus(Reservation.ReservationStatus.PENDING);
        }

        Reservation savedReservation = reservationRepository.save(reservation);
        return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        System.out.println("Reservations found: " + reservations.size());
        if (reservations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<ReservationDTO> dtoList = reservations.stream()
                .map(ReservationDTO::new)
                .toList();

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity<List<ReservationDTO>> getMyReservations(Authentication authentication, Reservation reservation) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String login = authentication.getName();
        Optional<User> userOpt = userRepository.findByLogin(login);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<Reservation> reservations = reservationService.getReservationsByUser(userOpt.get());
        if (reservations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ReservationDTO> dtoList = reservations.stream()
                .map(ReservationDTO::new)
                .toList();

        return ResponseEntity.ok(dtoList);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservationDetails) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(id);
        if (reservationOpt.isPresent()) {
            Reservation existingReservation = reservationOpt.get();
            // Nur Felder updaten, nicht den User (für mehr Sicherheit)
            existingReservation.setReservationDate(reservationDetails.getReservationDate());
            existingReservation.setAmountPeople(reservationDetails.getAmountPeople());
            existingReservation.setReservationStatus(reservationDetails.getReservationStatus());
            reservationRepository.save(existingReservation);
            return new ResponseEntity<>(existingReservation, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ReservationDTO> updateReservationStatus(@PathVariable Long id, @RequestBody Map<String, String> statusPayload) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(id);
        if (reservationOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Reservation reservation = reservationOpt.get();

        String newStatus = statusPayload.get("status");
        if (newStatus == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Reservation.ReservationStatus statusEnum = Reservation.ReservationStatus.valueOf(newStatus);
            reservation.setReservationStatus(statusEnum);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        reservationRepository.save(reservation);

        return ResponseEntity.ok(new ReservationDTO(reservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
