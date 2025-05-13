package com.example.foodordersystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuResponseDto {
    private long id;
    private String menuName;
    private String menuDescription;
}
