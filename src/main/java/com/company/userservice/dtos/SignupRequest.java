package com.company.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SignupRequest {
    private String email;
    private String password;
}
