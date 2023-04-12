package com.techacademy.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

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
    @Valid
    private Authentication authentication;

    @OneToMany(mappedBy="employee", cascade = CascadeType.ALL)
    @Valid
    private List<Report> report;

    @OneToMany(mappedBy="employee", cascade = CascadeType.ALL)
    @Valid
    private List<TimeCard> timecard;

    @Column(length=20,nullable=false)
    @NotEmpty
    @Length(max=20)
    private String name;

    @Column(nullable=false)
    private Integer deleteFlag;

    @Column(nullable=false,updatable=false,columnDefinition="TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(nullable=false,columnDefinition="TIMESTAMP")
    private LocalDateTime updatedAt;



}
