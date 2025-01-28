package com.company.userservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Role extends BaseModel {

    // because roles can be extensible A organisation can have so many roles
    private String value;

}
