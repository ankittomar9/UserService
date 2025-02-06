package com.company.userservice.services;


import com.company.userservice.exceptions.PasswordMismatchException;
import com.company.userservice.exceptions.UserNotRegisteredException;
import com.company.userservice.models.User;
import exceptions.UserAlreadyExistException;
import org.antlr.v4.runtime.misc.Pair;

public interface IAuthService {

    User signup (String email, String password) throws UserAlreadyExistException;

    Pair<User,String> login(String email, String password) throws UserNotRegisteredException, PasswordMismatchException;
}
