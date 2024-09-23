package com.example.assignment2springboot.login;

import com.example.assignment2springboot.dto.LoginDetails;
import com.example.assignment2springboot.dto.AdminDTO;
import com.example.assignment2springboot.entity.Admin;
import com.example.assignment2springboot.util.DTO_Factory;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class LoginController {
    //Initialises loginService and dto_factory for use in this class
    private final LoginService loginService;
    private final DTO_Factory dto_factory;

    //Creates a post mapping at '/login' which takes a username and password in a LoginDetails dto as details
    @PostMapping(path = "/login")
    public AdminDTO checkCredentials(@RequestBody LoginDetails details) throws Exception{
        //Uses the checkCredentials method from loginService to check the username and password are valid
        Admin admin = loginService.checkCredentials(details);

        //Uses the admin returned by the loginService to create an AdminDTO to return to the user
        return dto_factory.create(admin);
    }

    //Creates a post mapping at 'logout/' + the username for logging out. Calls the logout method from loginService
    @PostMapping(path = "/logout/{username}")
    public void logout(@PathVariable(name = "username") String username){
        loginService.logout(username);
    }
}
