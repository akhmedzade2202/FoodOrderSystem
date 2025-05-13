package com.example.foodordersystem.repository;

import com.example.foodordersystem.entity.Category;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByCategoryName(@NotBlank(message = "categoryName must not be null or blank") String name);

    Optional<Category> findByCategoryName(String categoryName);
}
