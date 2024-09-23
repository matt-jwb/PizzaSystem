package com.example.assignment2springboot.pizza;

import com.example.assignment2springboot.dto.PizzaDetails;
import com.example.assignment2springboot.entity.Admin;
import com.example.assignment2springboot.entity.AdminRepository;
import com.example.assignment2springboot.entity.Pizza;
import com.example.assignment2springboot.entity.PizzaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PizzaControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Test
    void addPizza_adds_pizza_to_database_with_correct_input(){
        //Setup
        Admin admin = new Admin();
        admin.setUsername("test");
        admin.setPassword("45c8951c55569e87664515822b49c99b");
        admin.setToken("testToken");
        adminRepository.save(admin);
        PizzaDetails details = new PizzaDetails("Test pizza", "Testeroni", "12.99");

        //Main
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, admin.getToken());

        boolean test = restTemplate.postForObject(
                "/pizza/add",
                new HttpEntity(details, headers),
                boolean.class
        );

        //Assert
        Optional<Pizza> pizza = pizzaRepository.findByIngredients("Testeroni");
        assertTrue(pizza.isPresent());
        assertTrue(test);

        //Teardown
        adminRepository.delete(admin);
        pizzaRepository.delete(pizza.get());
    }

    @Test
    void addPizza_returns_error_with_no_input(){
        //Setup
        Admin admin = new Admin();
        admin.setUsername("test");
        admin.setPassword("45c8951c55569e87664515822b49c99b");
        admin.setToken("testToken");
        adminRepository.save(admin);

        //Main
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, admin.getToken());

        try {
            boolean test = restTemplate.postForObject(
                    "/pizza/add",
                    new HttpEntity(headers),
                    boolean.class
            );
            fail();
        }
        catch (Exception e){
            assertEquals(NullPointerException.class, e.getClass());
        }

        //Teardown
        adminRepository.delete(admin);
    }

    @Test
    void addPizza_returns_error_with_invalid_input() {
        //Setup
        Admin admin = new Admin();
        admin.setUsername("test");
        admin.setPassword("45c8951c55569e87664515822b49c99b");
        admin.setToken("testToken");
        adminRepository.save(admin);
        PizzaDetails details = new PizzaDetails();

        //Main
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, admin.getToken());

        try {
            boolean test = restTemplate.postForObject(
                    "/pizza/add",
                    new HttpEntity(details, headers),
                    boolean.class
            );
            fail();
        }
        catch (Exception e){
            assertEquals(RestClientException.class, e.getClass());
        }

        //Assert
        Optional<Pizza> pizza = pizzaRepository.findByIngredients(null);
        assertFalse(pizza.isPresent());

        //Teardown
        adminRepository.delete(admin);
    }

    @Test
    void addPizza_returns_unauthorized_with_invalid_auth() {
        //Setup
        Admin admin = new Admin();
        admin.setUsername("test");
        admin.setPassword("45c8951c55569e87664515822b49c99b");
        admin.setToken("testToken");
        adminRepository.save(admin);
        PizzaDetails details = new PizzaDetails("Test pizza", "Testeroni", "12.99");

        //Main
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "notToken");

        try {
            boolean test = restTemplate.postForObject(
                    "/pizza/add",
                    new HttpEntity(details, headers),
                    boolean.class
            );
            fail();
        }
        catch (Exception e){
            assertEquals(ResourceAccessException.class, e.getClass());
        }

        //Assert
        Optional<Pizza> pizza = pizzaRepository.findByIngredients("Testeroni");
        assertFalse(pizza.isPresent());

        //Teardown
        adminRepository.delete(admin);
    }
}