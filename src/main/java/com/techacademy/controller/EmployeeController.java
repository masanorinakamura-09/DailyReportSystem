package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employeelist")
    public String getEmployeeList(Model model) {

        model.addAttribute("employeelist",service.getEmployeeList());
        model.addAttribute("index",service.getIndex());

        return "DailyReportSystem/employeeList";
    }
    @GetMapping("/employeeDetail/{id}")
    public String getEmployeeDetail(@PathVariable("id") Integer id,Model model) {

        model.addAttribute("employeedetail",service.getEmployeeDetail(id));
        return "DailyReportSystem/employeeDetail";
    }


    @GetMapping("/employeeregister")
    public String getEmployeeRegister(){
        return "DailyReportSystem/employeeRegister";
    }



}
