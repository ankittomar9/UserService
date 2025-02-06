package com.company.userservice.controllers;

import com.company.userservice.dtos.LoginRequest;
import com.company.userservice.dtos.SignupRequest;
import com.company.userservice.dtos.UserDto;
import com.company.userservice.exceptions.PasswordMismatchException;
import com.company.userservice.exceptions.UserNotRegisteredException;
import com.company.userservice.models.User;
import com.company.userservice.services.IAuthService;
import exceptions.UserAlreadyExistException;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
    public ResponseEntity<UserDto>  signup(@RequestBody SignupRequest signupRequest){
      try{
          User user = authService.signup(signupRequest.getEmail(),signupRequest.getPassword());
          return new ResponseEntity<>(from(user), HttpStatus.CREATED);
      }catch (UserAlreadyExistException exception){
          return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
      }


    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest loginRequest){
       try{
           Pair<User,String> response=authService.login(loginRequest.getEmail(), loginRequest.getPassword());
           MultiValueMap<String,String> headers=new LinkedMultiValueMap<>();
           headers.add(HttpHeaders.SET_COOKIE, response.b);
           return new ResponseEntity<>(from(response.a),headers, HttpStatus.OK);
       }catch(UserNotRegisteredException exception){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch(PasswordMismatchException exception){
         return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
       }
    }

    public UserDto from (User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        return userDto;
    }

}
