package com.company.userservice.services;


import com.company.userservice.exceptions.PasswordMismatchException;
import com.company.userservice.exceptions.UserNotRegisteredException;
import com.company.userservice.models.User;
import exceptions.UserAlreadyExistException;

public interface IAuthService {

    User signup (String email, String password) throws UserAlreadyExistException;

    User login(String email,String password) throws UserNotRegisteredException, PasswordMismatchException;
}
