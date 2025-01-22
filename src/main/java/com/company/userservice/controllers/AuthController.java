package com.company.userservice.controllers;

import com.company.userservice.dtos.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    //User dto is return because resource what is created should same be return
    public UserDto signup(@RequestBody signupRequest){
        return null;

    }
}
