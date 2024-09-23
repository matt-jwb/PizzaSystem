package com.example.assignment2springboot;

import com.example.assignment2springboot.login.LoginService;
import lombok.AllArgsConstructor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@WebFilter(urlPatterns = {"/*"})
public class AuthFilter implements Filter {
    //Initialises loginService for use in this class
    private final LoginService loginService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        //Gets request information
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String requestURI = request.getRequestURI().toLowerCase();
        String token = request.getHeader("AUTHORIZATION");

        //If the login screen is being accessed
        if (requestURI.contains("login")){
            //Allow access - The login screen must be accessible when logged out
            filterChain.doFilter(servletRequest, servletResponse);
        }
        //If the token is valid (user is logged in)
        else if(userIsAuthorised(token)){
            //Allow access
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else{
            //Deny access, send an Unauthorized error
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    //Defines a method to check if the user is authorised. When called it uses the checkToken method from loginService
    public boolean userIsAuthorised(String token) {
        return loginService.checkToken(token);
    }
}