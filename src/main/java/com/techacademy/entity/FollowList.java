package com.techacademy.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.Data;



@Data
@Entity
@Table(name="follower_list")
public class FollowList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name="employee_id",referencedColumnName="id",nullable=false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="follower_id",referencedColumnName="id",nullable=false)
    private Employee follower;

}
