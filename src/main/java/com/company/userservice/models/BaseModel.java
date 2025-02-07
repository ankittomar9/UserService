package com.company.userservice.models;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatically increment
    // the id auto does not do that it figures best by scanning the DB and select appropriate
    private Long id;  // UUID can also be implemented
    private Date createdAt;
    private Date lastUpdatedAt;
    private Status status; // for soft delete use flag


}
