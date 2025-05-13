package com.example.foodordersystem.repository;

import com.example.foodordersystem.entity.Menu;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    boolean existsByMenuName(@NotEmpty(message = " this section must not be a null") @NotNull String menuName);

    Optional<Menu> findByMenuName(String menuName);
}
