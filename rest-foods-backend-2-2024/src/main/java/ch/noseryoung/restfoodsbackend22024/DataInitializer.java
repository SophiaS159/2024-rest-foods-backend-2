package ch.noseryoung.restfoodsbackend22024;

import ch.noseryoung.restfoodsbackend22024.repository.FoodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(FoodRepository foodRepository) {
        return args -> {
            Food food1 = new Food(null, "Pizza Margherita", "Tomaten, Mozzarella, Basilikum", 12.50, "Pizza");
            Food food2 = new Food(null, "Spaghetti Carbonara", "Pasta mit Speck und Rahmsauce", 14.00, "Pasta");
            Food food3 = new Food(null, "Cheeseburger", "Rindfleisch, Cheddar, Salat, Tomate", 11.90, "Burger");
            Food food4 = new Food(null, "Caesar Salad", "Römersalat, Croutons, Parmesan, Caesar-Dressing", 9.50, "Salat");

            foodRepository.saveAll(List.of(food1, food2, food3, food4));

            System.out.println("✅ Test-Food-Daten wurden erstellt.");
        };
    }
}
