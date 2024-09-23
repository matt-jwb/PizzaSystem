package com.example.assignment2springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pizzas")
public class Pizza {
    @Id
    private int id;
    private String pizza_name;
    private String ingredients;
    private String price;
}
