package com.techacademy.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.service.EmployeeService;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("report")
public class ReportController {
    private final ReportService service;
    private final EmployeeService employeeservice;


    public ReportController(ReportService service, EmployeeService employeeservice) {
        this.service = service;
        this.employeeservice = employeeservice;
    }

    @GetMapping("/reportlist")
    public String getReportList(@AuthenticationPrincipal UserDetail userdetail,Model model) {
        List<Report> report=service.getReportList();
        model.addAttribute("reportlist", service.getReportList());
        model.addAttribute("index", service.getIndex());
        model.addAttribute("username",userdetail.getLoginName());
        model.addAttribute("userroll",userdetail.getUserRoll());

        return "DailyReportSystem/reportList";

    }

    @GetMapping("reportregister")
    public String getReportReigister(Report report,@AuthenticationPrincipal UserDetail userdetail,Model model) {
        LocalDate localDate=LocalDate.now();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date=localDate.format(dateTimeFormatter);

        model.addAttribute("report",report);
        model.addAttribute("username",userdetail.getLoginName());
        model.addAttribute("userroll",userdetail.getUserRoll());
        model.addAttribute("currentdate",date);
        return "DailyReportSystem/reportRegister";

    }

    @PostMapping("reportregister")
    public String postReportRegister(@RequestParam(name="content") String content,@RequestParam(name="date") String date,Report report,
            @AuthenticationPrincipal UserDetail userdetail,Model model) {
        LocalDate localDate=LocalDate.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime now=LocalDateTime.now();
        Employee employee=userdetail.getEmployee();


        report.setContent(content);
        report.setReportDate(localDate);
        report.setEmployee(employee);
        report.setCreatedAt(now);
        report.setUpdatedAt(now);

        service.saveReport(report);

        return "redirect:/report/reportlist";

    }


}
