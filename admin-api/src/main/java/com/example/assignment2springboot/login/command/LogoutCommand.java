package com.example.assignment2springboot.login.command;

import com.example.assignment2springboot.entity.Admin;
import com.example.assignment2springboot.entity.AdminRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class LogoutCommand implements Command {
    //Initialises requirements for use in this class
    private final AdminRepository adminRepository;
    private final String username;

    //Defines the execute method for this command
    public Object execute(){
        //Finds the user trying to logout
        Optional<Admin> attemptedLogout = adminRepository.findByUsername(username);

        //If the user can be found their token is revoked (set to null)
        if (attemptedLogout.isPresent()){
            Admin admin = attemptedLogout.get();
            admin.setToken(null);
            adminRepository.save(admin);
        }
        //If the user cannot be found 'null' will be returned - nothing will happen
        return null;
    }
}
