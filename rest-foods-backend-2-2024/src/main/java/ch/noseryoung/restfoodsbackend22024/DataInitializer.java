package ch.noseryoung.restfoodsbackend22024;

import ch.noseryoung.restfoodsbackend22024.model.Food;
import ch.noseryoung.restfoodsbackend22024.model.Reservation;
import ch.noseryoung.restfoodsbackend22024.model.User;
import ch.noseryoung.restfoodsbackend22024.repository.FoodRepository;
import ch.noseryoung.restfoodsbackend22024.repository.ReservationRepository;
import ch.noseryoung.restfoodsbackend22024.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(FoodRepository foodRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        return args -> {
            // Beispiel-User
            User user1 = new User(null, "hAEkKed", "haekked@example.com", "securepass", "CUSTOMER");
            User user2 = new User(null, "max", "max@example.com", "max1234", "ADMIN");

            user1 = userRepository.save(user1);
            user2 = userRepository.save(user2);

            // Beispiel-Foods
            Food pizza = new Food(null, "Pizza Margherita", "Leckere Pizza mit Tomaten und Käse", 12.50, "Italienisch");
            Food burger = new Food(null, "Classic Burger", "Rindfleisch-Burger mit Pommes", 14.90, "Amerikanisch");
            Food ramen = new Food(null, "Tonkotsu Ramen", "Japanische Nudelsuppe mit Schweinefleisch", 16.00, "Japanisch");

            foodRepository.saveAll(List.of(pizza, burger, ramen));

            // Beispiel-Reservationen für User1
            Reservation res1 = new Reservation();
            res1.setUserId(user1.getId());
            res1.setUsername(user1.getUsername());
            res1.setReservation_date(LocalDateTime.now().plusDays(1));
            res1.setAmountPeople(2);

            Reservation res2 = new Reservation();
            res2.setUserId(user1.getId());
            res2.setUsername(user1.getUsername());
            res2.setReservation_date(LocalDateTime.now().plusDays(3));
            res2.setAmountPeople(4);

            // Beispiel-Reservation für User2
            Reservation res3 = new Reservation();
            res3.setUserId(user2.getId());
            res3.setUsername(user2.getUsername());
            res3.setReservation_date(LocalDateTime.now().plusDays(5));
            res3.setAmountPeople(6);

            reservationRepository.saveAll(List.of(res1, res2, res3));

            System.out.println("✅ Test-Daten für Foods, Users und Reservations wurden erstellt.");
        };
    }
}
