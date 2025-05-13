package com.example.foodordersystem.repository;

import com.example.foodordersystem.entity.Food;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    boolean existsByFoodName(@NotBlank(message = "foodName must not be null or blank") String foodName);

    Optional<Food> findByFoodName(String foodName);
}
