package com.example.foodordersystem.enums;

public enum OrderStatus {

    PENDING,     // Sifariş yaradıldı, lakin hələ təsdiqlənməyib
    CONFIRMED,   // Restoran tərəfindən təsdiqləndi
    PREPARING,   // Yemək hazırlanır
    OUT_FOR_DELIVERY, // Kuryer sifarişi çatdırır
    DELIVERED,   // Sifariş müştəriyə çatdırıldı
    CANCELLED

}
