package ch.noseryoung.restfoodsbackend22024.service;

import ch.noseryoung.restfoodsbackend22024.model.Food;
import ch.noseryoung.restfoodsbackend22024.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Optional<Food> getFoodById(Long id) {
        return foodRepository.findById(id);
    }

    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }

    public List<Food> searchFoodsByName(String name) {
        return foodRepository.findByNameContainingIgnoreCase(name);
    }
}
