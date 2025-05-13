package com.example.foodordersystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FoodRequestDto {
    @NotBlank(message = "foodName must not be null or blank")
    private String foodName;
    @NotBlank(message = "category description must not be null or blank")
    private String description;
    @NotBlank(message = "categoryName description must not be null or blank")
    private String categoryName;
}
