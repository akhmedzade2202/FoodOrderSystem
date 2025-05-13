package com.example.foodordersystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotBlank(message = "categoryName must not be null or blank")
    private String categoryName;
    @NotBlank(message = "category description must not be null or blank")
    private String description;
    @NotBlank(message = "menuName description must not be null or blank")
    private String menuName;
}
