package com.techacademy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    List<Report> findByEmployee(Employee employee);

    @Query("SELECT r FROM Report r INNER JOIN Employee e ON r.employee = e.id AND e.deleteFlag=0")
    List<Report> getReportList();
}
