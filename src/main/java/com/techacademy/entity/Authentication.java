package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="authentication")
public class Authentication {

    @Id
    @Column(length=20,nullable=false)
    private String code;

    @Column(length=255,nullable=false)
    private String password;

    @Column(length=10,nullable=false)
    private String role;

    @OneToOne
    @JoinColumn(name="employee_id")
    private Employee employee;


}
