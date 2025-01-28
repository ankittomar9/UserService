package com.company.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel {

    private String email;
    private String password;
    //Userid will be fetched from BaseModel
//    Relation between user and role  --- cardinality
         @ManyToMany
        private  List<Role> roles = new ArrayList(); // initialize with null or empty values

}
//user   role
//1       m
//m        1
//m        m