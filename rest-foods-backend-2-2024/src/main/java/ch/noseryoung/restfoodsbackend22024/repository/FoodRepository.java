package ch.noseryoung.restfoodsbackend22024.repository;

import ch.noseryoung.restfoodsbackend22024.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}