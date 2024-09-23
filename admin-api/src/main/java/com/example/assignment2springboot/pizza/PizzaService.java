package com.example.assignment2springboot.pizza;

import com.example.assignment2springboot.dto.PizzaDetails;
import com.example.assignment2springboot.entity.Pizza;
import com.example.assignment2springboot.entity.PizzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PizzaService {
    //Initialises pizzaRepository for use in this class
    private final PizzaRepository pizzaRepository;

    //Defines a method to add a pizza to the database. Takes a PizzaDetails dto as details
    public boolean addPizza(PizzaDetails details){
        //Creates a new Pizza Entity
        Pizza pizza = new Pizza();
        //Sets the properties for the pizza using the PizzaDetails dto
        pizza.setPizza_name(details.getName());
        pizza.setIngredients(details.getIngredients());
        pizza.setPrice(details.getPrice());
        //Saves the pizza to the database and returns true - indicating that the function has worked
        pizzaRepository.save(pizza);
        return true;
    }
}
