package com.example.assignment2springboot.pizza;

import com.example.assignment2springboot.dto.PizzaDetails;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class PizzaController {
    //Initialises pizzaService for use in this class
    private final PizzaService pizzaService;

    //Creates a post mapping at '/pizza/add' which takes a PizzaDetails dto as details
    @PostMapping(path = "/pizza/add")
    public boolean addPizza(@RequestBody PizzaDetails details) {
        //Calls the addPizza method from pizza service passing the PizzaDetails
        return pizzaService.addPizza(details);
    }
}
