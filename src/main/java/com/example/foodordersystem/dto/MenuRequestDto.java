package com.example.foodordersystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class MenuRequestDto {

    @NotBlank(message = "menuName must not be null or blank")
    private String menuName;


    @NotBlank(message = "menuDescription must not be null or blank")
    private String menuDescription;
}
