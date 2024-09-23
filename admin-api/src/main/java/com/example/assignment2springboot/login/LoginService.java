package com.example.assignment2springboot.login;

import com.example.assignment2springboot.dto.LoginDetails;
import com.example.assignment2springboot.entity.Admin;
import com.example.assignment2springboot.entity.AdminRepository;
import com.example.assignment2springboot.login.command.Command;
import com.example.assignment2springboot.login.command.CommandFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final CommandFactory commandFactory;

    //Defines a method to create and execute the CheckCredentialsCommand from the commandFactory
    public Admin checkCredentials(LoginDetails details) throws Exception{
        return
                (Admin)commandFactory
                        .create(Command.CHECK_CREDENTIALS, details)
                        .execute();
    }

    //Defines a method to create and execute the CheckTokenCommand from the commandFactory
    public boolean checkToken(String token) {
        try {
            return
                    (boolean) commandFactory
                            .create(Command.CHECK_TOKEN, token)
                            .execute();
        }
        catch (Exception e){
            return false;
            //Try catch is required because .execute() can throw an exception for other commands
            //For this command no exception is thrown meaning this will never execute
        }
    }

    //Defines a method to create and execute the Logout command from the commandFactory
    public void logout(String username){
        try{
            commandFactory.create(Command.LOGOUT, username).execute();
        }
        catch (Exception e){
            //Try catch is required because .execute() can throw an exception for other commands
            //For this command no exception is thrown meaning this will never execute
        }
    }
}
