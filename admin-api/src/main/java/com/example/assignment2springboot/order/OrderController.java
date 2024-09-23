package com.example.assignment2springboot.order;

import com.example.assignment2springboot.entity.Order;
import com.example.assignment2springboot.dto.OrderListDTO;
import com.example.assignment2springboot.util.DTO_Factory;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
public class OrderController {
    //Initialises orderService and dto_factory for use in this class
    private final OrderService orderService;
    private final DTO_Factory dto_factory;

    //Creates a get mapping at '/order/viewall'
    @GetMapping(path = "/order/viewall")
    public OrderListDTO viewAll(){
        //Creates an empty List of orders
        List<Order> orderDTOList = new ArrayList<>();

        //Populates the list with an Order entity for each order in the database
        orderService
                .getAllOrders()
                .forEach(order ->
                        orderDTOList.add(
                                new Order(
                                        order.getId(),
                                        order.getUser_id(),
                                        order.getDate_time(),
                                        order.getCollection_delivery(),
                                        order.getDelivery_address(),
                                        order.getPrice(),
                                        order.getPizzas(),
                                        order.getDeal(),
                                        order.getDeal_price()
                                )
                        ));

        //Uses the dto_factory to create an OrderListDTO and returns it
        return dto_factory.create(orderDTOList);
    }
}
