package com.example.assignment2springboot.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, String> {
    Optional<Pizza> findByIngredients(String ingredients);
}
