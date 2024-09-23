package com.example.assignment2springboot.login.command;

import com.example.assignment2springboot.dto.LoginDetails;
import com.example.assignment2springboot.entity.Admin;
import com.example.assignment2springboot.entity.AdminRepository;
import com.example.assignment2springboot.exception.InvalidCredentialsException;
import com.example.assignment2springboot.util.StringHasher;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
public class CheckCredentialsCommand implements Command {
    //Initialises requirements for use in this class
    private final AdminRepository adminRepository;
    private final LoginDetails details;
    private final StringHasher stringHasher;

    //Defines the execute method for this command
    @Override
    public Admin execute() throws Exception {
        //Attempts to find an admin in the adminRepository with the username provided
        Optional<Admin> attemptedLogin = adminRepository.findByUsername(details.getUsername());

        //If the admin does not exist an InvalidCredentialsException is thrown
        if(!attemptedLogin.isPresent()){
            throw new InvalidCredentialsException();
        }

        //If the admin does exist an Admin object is created and the password is hashed before being stored in the database
        Admin admin = attemptedLogin.get();
        details.setPassword(stringHasher.hashString(details.getPassword()));

        //If the hashed password is not equal to the password stored in the database (also hashed) an InvalidCredentialsException is thrown
        if (!admin.getPassword().equals(details.getPassword())){
            throw new InvalidCredentialsException();
        }

        //If the passwords match an auth token is generated
        String token = stringHasher.hashString(admin.getUsername() + LocalDateTime.now().toString());
        admin.setToken(token);

        //The updated admin (now with a token) is saved to the database and returned for use in the AdminDTO
        return adminRepository.save(admin);
    }
}
