package com.techacademy.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name="timecard")
public class TimeCard {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate timecardDate;

    @ManyToOne
    @JoinColumn(name="employee_id",referencedColumnName="id",nullable=false)
    private Employee employee;

    @Column(nullable=true)
    @DateTimeFormat(pattern="HH:mm:ss")
    private LocalTime workTime;

    @Column(nullable=true)
    @DateTimeFormat(pattern="HH:mm:ss")
    private LocalTime leavingworkTime;
}
