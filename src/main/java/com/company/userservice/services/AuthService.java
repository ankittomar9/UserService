package com.company.userservice.services;

import com.company.userservice.exceptions.PasswordMismatchException;
import com.company.userservice.exceptions.UserNotRegisteredException;
import com.company.userservice.models.Role;
import com.company.userservice.models.User;
import com.company.userservice.repos.UserRepo;
import exceptions.UserAlreadyExistException;
import io.jsonwebtoken.Jwts;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {

        @Autowired
        private UserRepo userRepo;

        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User signup(String email, String password) throws UserAlreadyExistException {
        Optional<User> userOptional=userRepo.findByEmail(email);
        if(userOptional.isPresent()){
        throw new UserAlreadyExistException("Please try logging..........");
        }

        User user = new User();
        user.setEmail(email);
       // user.setPassword(password);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setCreatedAt(new Date());
        user.setLastUpdatedAt(new Date());
         Role role = new Role();
        role.setValue("CUSTOMER");

        //Saving roles giving user  a default role
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        userRepo.save(user);
        return user;
    }

    @Override
    public Pair<User,String>  login(String email, String password) throws UserNotRegisteredException, PasswordMismatchException {
        Optional<User> userOptional=userRepo.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new UserNotRegisteredException("Please signup first.....");
        }
        String storedPassword = userOptional.get().getPassword();
        if(!bCryptPasswordEncoder.matches(password, storedPassword)){
        //if(!password.equals(storedPassword)){
            throw new PasswordMismatchException("Please add correct password......");
        }
        //Generating JWT
        String message = "{\n" +
                "   \"email\": \"ankit@gmail.com\",\n" +
                "   \"roles\": [\n" +
                "      \"Learner\",\n" +
                "      \"buddy\"\n" +
                "   ],\n" +
                "   \"expirationDate\": \"2ndApril2025\"\n" +
                "}";

        byte[] content =message.getBytes(StandardCharsets.UTF_8);
        String token = Jwts.builder().content(content).compact();





        return new Pair<User,String>(userOptional.get(),token);
    }
}
