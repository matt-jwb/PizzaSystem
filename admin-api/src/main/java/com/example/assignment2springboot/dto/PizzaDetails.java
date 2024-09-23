package com.example.assignment2springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PizzaDetails {
    private String name;
    private String ingredients;
    private String price;
}
