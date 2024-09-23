package com.example.assignment2springboot.login.command;

//Creates a command interface with the option for three commands
public interface Command {
    int CHECK_CREDENTIALS = 1;
    int CHECK_TOKEN = 2;
    int LOGOUT = 3;

    //Defines an execute method to be overrided in the command classes
    Object execute() throws Exception;
}
