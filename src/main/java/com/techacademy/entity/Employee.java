package com.techacademy.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Data;

@Data
@Entity
@Table(name="employee")
@Where(clause = "delete_flag = 0")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy="employee", cascade = CascadeType.ALL)
    private Authentication authentication;

    @Column(length=20,nullable=false)
    private String name;

    @Column(nullable=false)
    private Integer deleteFlag;

    @Column(nullable=false,updatable=false,columnDefinition="TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(nullable=false,columnDefinition="TIMESTAMP")
    private LocalDateTime updateAt;



}
