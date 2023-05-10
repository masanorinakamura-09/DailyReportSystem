package com.techacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techacademy.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
