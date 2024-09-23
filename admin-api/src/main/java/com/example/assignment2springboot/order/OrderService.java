package com.example.assignment2springboot.order;

import com.example.assignment2springboot.entity.Order;
import com.example.assignment2springboot.entity.OrderRepository;
import com.example.assignment2springboot.util.StringHasher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    //Initialises orderRepository for use in this class
    private final OrderRepository orderRepository;
    //Defines a method which returns a list of all the orders in the orderRepository
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
