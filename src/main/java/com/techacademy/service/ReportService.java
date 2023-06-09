package com.techacademy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;

@Service
public class ReportService {
    public final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public List<Report> getReportList(Integer authentication){
            return repository.findByAuthentication(authentication);
    }

    public List<Report> getEmployeeReportList(Employee employee){
        return repository.findByEmployee(employee);
}


    public long getIndex() {
        return repository.count();
    }

    public Report getReport(Integer id) {
        return repository.findById(id).get();
    }

    @Transactional
    public Report saveReport(Report report) {
        return repository.save(report);
    }

}
