package com.company.userservice.controllers;

import com.company.userservice.dtos.LoginRequest;
import com.company.userservice.dtos.SignupRequest;
import com.company.userservice.dtos.UserDto;
import com.company.userservice.models.User;
import com.company.userservice.services.IAuthService;
import exceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;
    //User dto is return because resource what is created should same be return
    // and we are returning user dto and not user object because we don't want to show the password
    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignupRequest signupRequest){
      try{
          User user = authService.signup(signupRequest.getEmail(),signupRequest.getPassword());
      }catch (UserAlreadyExistException exception){
          return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
      }


    }
    @PostMapping("login")
    public UserDto login(@RequestBody LoginRequest loginRequest){
        return null;
    }

}
