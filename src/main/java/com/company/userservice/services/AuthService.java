package com.company.userservice.services;

import com.company.userservice.models.Role;
import com.company.userservice.models.User;
import com.company.userservice.repos.UserRepo;
import exceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {

        @Autowired
        private UserRepo userRepo;

    @Override
    public User signup(String email, String password) throws UserAlreadyExistException {
        Optional<User> userOptional=userRepo.findByEmail(email);
        if(userOptional.isPresent()){
        throw new UserAlreadyExistException("Please try logging..........");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
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
    public User login(String email, String password) {
        return null;
    }
}
