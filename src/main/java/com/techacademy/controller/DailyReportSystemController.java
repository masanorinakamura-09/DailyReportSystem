package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("DailyReportSystem")
public class DailyReportSystemController {
    private final EmployeeService service;

    public DailyReportSystemController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/toppage")
    public String getTopPage(Model model) {

        model.addAttribute("employeelist",service.getEmployeeList());
        return "DailyReportSystem/toppage";
    }

}
