package com.example.assignment2springboot.login.command;

import com.example.assignment2springboot.entity.Admin;
import com.example.assignment2springboot.entity.AdminRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CheckTokenCommand implements Command {
    //Initialises requirements for use in this class
    private final AdminRepository adminRepository;
    private final String token;

    //Defines the execute method for this command
    @Override
    public Object execute(){
        //If no authorization header is provided 'token' will be equal to null
        //This would make attemptedAuth return true because all the logged out admins would have a token equal to null
        //So before attemptedAuth is checked the method will return false if no authorization header is provided (token is null)
        if (token == null){
            return false;
        }

        //Attempts to find the token in the database. If it is present, returns true. If it is not, returns false.
        Optional<Admin> attemptedAuth = adminRepository.findByToken(token);
        return attemptedAuth.isPresent();
    }
}
