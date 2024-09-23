package com.example.assignment2springboot.util;

import com.example.assignment2springboot.dto.AdminDTO;
import com.example.assignment2springboot.dto.OrderListDTO;
import com.example.assignment2springboot.entity.Admin;
import com.example.assignment2springboot.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DTO_Factory {

    //Creates an AdminDTO using an Admin entity
    public AdminDTO create(Admin admin){
        return new AdminDTO(admin.getUsername(), admin.getToken());
    }

    //Creates an OrderListDTO using a list of orders
    public OrderListDTO create(List<Order> orderDTOList){
        return new OrderListDTO(orderDTOList);
    }
}
