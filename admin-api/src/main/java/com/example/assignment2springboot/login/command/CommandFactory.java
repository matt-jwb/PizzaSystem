package com.example.assignment2springboot.login.command;

import com.example.assignment2springboot.dto.LoginDetails;
import com.example.assignment2springboot.entity.AdminRepository;
import com.example.assignment2springboot.util.StringHasher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.assignment2springboot.login.command.Command.*;

@RequiredArgsConstructor
@Component
public class CommandFactory {
    //Initialises requirements for use in this class
    private final AdminRepository adminRepository;
    private final StringHasher stringHasher;

    //Defines a method for creating a command, takes a commandCode to define which command and paramaters to provide to the command
    public Command create(int commandCode, Object... params){
        //Creates the appropriate command class based on the commandCode provided
        switch (commandCode){
            case CHECK_CREDENTIALS:
                return new CheckCredentialsCommand(adminRepository, (LoginDetails)params[0], stringHasher);
            case CHECK_TOKEN:
                return new CheckTokenCommand(adminRepository, (String)params[0]);
            case LOGOUT:
                return new LogoutCommand(adminRepository, (String)params[0]);
        }
        //If an invalid commandCode is passed, it will return null
        //This would only happen with incorrect code
        return null;
    }
}
