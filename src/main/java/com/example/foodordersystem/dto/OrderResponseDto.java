package com.example.foodordersystem.dto;

import com.example.foodordersystem.enums.OrderStatus;
import com.example.foodordersystem.enums.Payment;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDto {
    private String address;
    private String restaurantName;
    private Payment payment;

    @JsonInclude(JsonInclude.Include.NON_NULL) // yalnız CARD olduqda göstər
    private String cardNumber;

    private OrderStatus status;

    private List<String> foodNames;
}
