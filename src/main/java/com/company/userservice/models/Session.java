package com.company.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Session  extends BaseModel{
    private String token;

    @ManyToOne
    private User user;

}
//User session   m-1 sessions are inactive
//1      m
//1       m
//
//1:m