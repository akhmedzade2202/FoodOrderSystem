package com.example.foodordersystem.entity;

import com.example.foodordersystem.enums.OrderStatus;
import com.example.foodordersystem.enums.Payment;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String restaurantName;
    @Enumerated(EnumType.STRING)
    private Payment payment;
    private String cardNumber;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToMany
    @JoinTable(
            name = "order_foods",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private List<Food> foods;

}