package com.example.assignment2springboot.order;

import com.example.assignment2springboot.dto.OrderListDTO;
import com.example.assignment2springboot.entity.Admin;
import com.example.assignment2springboot.entity.AdminRepository;
import com.example.assignment2springboot.entity.Order;
import com.example.assignment2springboot.entity.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void viewAll_returns_json_with_valid_auth() {
        //Setup
        Admin admin = new Admin();
        admin.setUsername("test");
        admin.setPassword("45c8951c55569e87664515822b49c99b");
        admin.setToken("testToken");
        adminRepository.save(admin);
        Order order1 = new Order(1, 1, new Date(), "Collection", "12 pizza lane", "12.99", "1 test pizza", null, null);
        orderRepository.save(order1);

        //Main
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "testToken");
        OrderListDTO orderListDTO =
                restTemplate
                        .exchange(
                                "/order/viewall",
                                HttpMethod.GET,
                                new HttpEntity(headers),
                                OrderListDTO.class
                        )
                        .getBody();

        //Assert
        assertEquals(order1.getId(), orderListDTO.getOrderDTOList().get(0).getId());
        assertEquals(order1.getUser_id(), orderListDTO.getOrderDTOList().get(0).getUser_id());
        assertEquals(order1.getPizzas(), orderListDTO.getOrderDTOList().get(0).getPizzas());
        assertEquals(order1.getPrice(), orderListDTO.getOrderDTOList().get(0).getPrice());

        //Teardown
        adminRepository.delete(admin);
        orderRepository.deleteAll();
    }

    @Test
    void viewAll_returns_error_with_invalid_auth() {
        //Setup
        Admin admin = new Admin();
        admin.setUsername("test");
        admin.setPassword("45c8951c55569e87664515822b49c99b");
        admin.setToken("testToken");
        adminRepository.save(admin);
        Order order1 = new Order(1, 1, new Date(), "Collection", "12 pizza lane", "12.99", "1 test pizza", null, null);
        orderRepository.save(order1);

        //Main
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "notToken");
            ResponseEntity<String> response =
                    restTemplate
                            .postForEntity(
                                    "/order/viewall",
                                    new HttpEntity(headers),
                                    String.class
                            );
        }
        catch (Exception e){
            assertEquals(ResourceAccessException.class, e.getClass());
        }

        //Teardown
        adminRepository.delete(admin);
        orderRepository.deleteAll();
    }
}