package com.example.assignment2springboot.login;

import com.example.assignment2springboot.dto.LoginDetails;
import com.example.assignment2springboot.dto.AdminDTO;
import com.example.assignment2springboot.entity.Admin;
import com.example.assignment2springboot.entity.AdminRepository;
import com.example.assignment2springboot.exception.InvalidCredentialsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    AdminRepository adminRepository;

    @Test
    void checkCredentials_returns_AdminDTO_with_valid_details() {
        //Setup
        Admin admin = new Admin();
        admin.setUsername("test");
        admin.setPassword("45c8951c55569e87664515822b49c99b");
        adminRepository.save(admin);

        //Main
        LoginDetails details = new LoginDetails("test", "password");
        AdminDTO adminDTO =
                restTemplate
                        .postForObject(
                                "/login",
                                details,
                                AdminDTO.class
                        );
        //Assert
        Optional<Admin> admin2 = adminRepository.findByUsername("test");
        Admin admin3 = admin2.get();
        String token = admin3.getToken();
        assertEquals("test", adminDTO.getUsername());
        assertEquals(token, adminDTO.getToken());

        //Teardown
        adminRepository.delete(admin);
    }

    @Test
    void checkCredentials_throws_exception_with_invalid_details() {
        //Setup
        LoginDetails details = new LoginDetails("notusername", "notpassword");

        //Main
        ResponseEntity<String> response =
                restTemplate
                        .postForEntity(
                                "/login",
                                details,
                                String.class
                        );

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Username or password is invalid", response.getBody());
    }

    @Test
    void checkCredentials_throws_exception_with_invalid_username(){
        //Setup
        Admin admin = new Admin();
        admin.setUsername("test");
        admin.setPassword("45c8951c55569e87664515822b49c99b");
        adminRepository.save(admin);
        LoginDetails details = new LoginDetails("notusername", "password");

        //Main
        ResponseEntity<String> response =
                restTemplate
                        .postForEntity(
                                "/login",
                                details,
                                String.class
                        );

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Username or password is invalid", response.getBody());

        //Teardown
        adminRepository.delete(admin);
    }

    @Test
    void checkCredentials_throws_exception_with_invalid_password(){
        //Setup
        Admin admin = new Admin();
        admin.setUsername("test");
        admin.setPassword("45c8951c55569e87664515822b49c99b");
        adminRepository.save(admin);
        LoginDetails details = new LoginDetails("test", "notpassword");

        //Main
        ResponseEntity<String> response =
                restTemplate
                        .postForEntity(
                                "/login",
                                details,
                                String.class
                        );

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Username or password is invalid", response.getBody());

        //Teardown
        adminRepository.delete(admin);
    }

    @Test
    void logout_unsets_token_with_valid_token() {
        //Setup
        Admin admin = new Admin();
        admin.setUsername("test");
        admin.setPassword("45c8951c55569e87664515822b49c99b");
        admin.setToken("testToken");
        adminRepository.save(admin);

        //Main
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, admin.getToken());
        restTemplate.postForObject(
                "/logout/" + admin.getUsername(),
                new HttpEntity(headers),
                Void.class
        );

        //Assert
        Optional<Admin> loggedInAdmin = adminRepository.findByToken(admin.getToken());
        assertFalse(loggedInAdmin.isPresent());

        //Teardown
        adminRepository.delete(admin);
    }

    @Test
    void logout_does_nothing_with_invalid_username() {
        //Setup
        Admin admin = new Admin();
        admin.setUsername("test");
        admin.setPassword("45c8951c55569e87664515822b49c99b");
        admin.setToken("testToken");
        adminRepository.save(admin);

        //Main
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, admin.getToken());
        restTemplate.postForObject(
                "/logout/notAdmin",
                new HttpEntity(headers),
                Void.class
        );

        //Assert
        Optional<Admin> loggedInAdmin = adminRepository.findByToken(admin.getToken());
        assertTrue(loggedInAdmin.isPresent());

        //Teardown
        adminRepository.delete(admin);
    }

    @Test
    void logout_returns_unauthorised_with_invalid_token() {
        //Setup
        Admin admin = new Admin();
        admin.setUsername("test");
        admin.setPassword("45c8951c55569e87664515822b49c99b");
        admin.setToken("testToken");
        adminRepository.save(admin);

        //Main
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "notToken");
            restTemplate.postForObject(
                    "/logout/" + admin.getUsername(),
                    new HttpEntity(headers),
                    Void.class
            );
            fail();
        }
        catch (Exception e){
            Optional<Admin> loggedInAdmin = adminRepository.findByToken(admin.getToken());
            assertTrue(loggedInAdmin.isPresent());
            assertEquals(ResourceAccessException.class, e.getClass());
        }

        //Teardown
        adminRepository.delete(admin);
    }
}