package ch.noseryoung.restfoodsbackend22024.service;

import ch.noseryoung.restfoodsbackend22024.model.Food;
import ch.noseryoung.restfoodsbackend22024.repository.FoodRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class FoodDataLoader implements CommandLineRunner {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public void run(String... args) throws Exception {
        String[] jsonFiles = {
                "food_bbq.json",
                "food_desserts.json",
                "food_drinks.json",
                "food_soups&stews.json",
                "food_rice&noodles.json"
        };

        ObjectMapper mapper = new ObjectMapper();

        for (String jsonFile : jsonFiles) {
            InputStream is = new ClassPathResource(jsonFile).getInputStream();

            // Direkte Liste von Food Objekten mappen
            List<Food> foods = mapper.readValue(is, new TypeReference<List<Food>>() {});

            for (Food food : foods) {
                // Optional: Beschreibung kürzen, falls zu lang
                if (food.getDescription() != null && food.getDescription().length() > 255) {
                    food.setDescription(food.getDescription().substring(0, 252) + "...");
                }

                foodRepository.save(food);
            }

            System.out.println("Importiert " + foods.size() + " Gerichte aus " + jsonFile);
        }
    }
}
