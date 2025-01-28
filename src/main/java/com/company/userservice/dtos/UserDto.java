package com.company.userservice.dtos;

import com.company.userservice.models.Role;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private Long id;

    private String Name;

    private List<Role> roles = new ArrayList();

}
