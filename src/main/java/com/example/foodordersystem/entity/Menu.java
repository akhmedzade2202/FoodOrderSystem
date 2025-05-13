package com.example.foodordersystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Table(name="menu")
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String menuName;
    private String menuDescription;
    @OneToMany(mappedBy = "menu")
    private List<Category> categories;
}
