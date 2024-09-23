package com.example.assignment2springboot.exception;

public class InvalidCredentialsException extends Exception{
    public InvalidCredentialsException()
    {
        super("Username or password is invalid");
    }
}
