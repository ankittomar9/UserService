package com.company.userservice.security;

import com.company.userservice.models.User;
import com.company.userservice.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional=userRepo.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("Please do Sign up");
        }
        return new CustomUserDetails(userOptional.get());
   }

}
