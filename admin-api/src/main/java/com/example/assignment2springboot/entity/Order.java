package com.example.assignment2springboot.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private int id;
    private int user_id;
    private Date date_time;
    private String collection_delivery;
    private String delivery_address;
    private String price;
    private String pizzas;
    private String deal;
    private String deal_price;
}
