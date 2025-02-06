package com.company.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidateTokenDto {

    private String token;

    private Long userId;
}
