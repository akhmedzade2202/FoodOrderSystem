package com.example.foodordersystem.dto;

import lombok.Data;

@Data
public class CategoryResponseDto {
    private Long id;
    private String categoryName;
    private String description;
    private String menuName;
}
