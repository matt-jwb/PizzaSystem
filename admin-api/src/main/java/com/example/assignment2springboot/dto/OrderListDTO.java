package com.example.assignment2springboot.dto;

import com.example.assignment2springboot.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderListDTO {
    private List<Order> orderDTOList;
}
