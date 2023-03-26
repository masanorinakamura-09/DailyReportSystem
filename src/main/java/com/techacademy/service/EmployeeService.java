package com.techacademy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getEmployeeList(){
        return repository.findAll();
    }

    public long getIndex() {
        return repository.count();
    }

    public Employee getEmployeeDetail(Integer id) {

        return repository.findById(id).get();

    }


    @Transactional
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }







}
