package com.rakib.employee.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Employee extends BaseModel {
    private String name;
    private String phoneNo;
}
