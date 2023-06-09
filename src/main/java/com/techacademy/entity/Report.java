package com.techacademy.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name="report")
public class Report {

    public static enum Status{
        商談中,見積,受注,納品,キャンセル
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @PastOrPresent
    private LocalDate reportDate;

    @Column(length=255,nullable=false)
    @NotEmpty
    private String title;

    @Column(nullable=false)
    @Type(type="text")
    @NotEmpty
    private String content;

    @ManyToOne
    @JoinColumn(name="employee_id",referencedColumnName="id",nullable=false)
    private Employee employee;

    @Column(nullable=false,updatable=false,columnDefinition="TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(nullable=false,columnDefinition="TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(nullable=false)
    private Integer authentication;

    @Column(nullable=false)
    private Integer nice;

    @OneToOne
    @JoinColumn(name="customer_id",referencedColumnName="id",nullable=false)
    private Customer customer;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
