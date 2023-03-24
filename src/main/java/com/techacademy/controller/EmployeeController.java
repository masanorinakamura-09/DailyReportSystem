package com.techacademy.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techacademy.entity.Employee;
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
    public String getEmployeeRegister(@ModelAttribute Employee employee){

        return "DailyReportSystem/employeeRegister";
    }

    @PostMapping("/employeeregister")
    public String postEmployeeRegister(Employee employee){
        LocalDateTime now=LocalDateTime.now();

        if(employee.getAuthentication().getPassword()=="") {
            employee.getAuthentication().setPassword(null);
        }

        employee.setDeleteFlag(0);
        employee.setCreatedAt(now);
        employee.setUpdateAt(now);
        employee.getAuthentication().setEmployee(employee);
        service.saveEmployee(employee);

        return "redirect:/employee/employeeregister";
    }

    @GetMapping("/employeeUpdate/{id}")
    public String getEmployeeUpdate(@PathVariable("id") Integer id,Model model) {

    model.addAttribute("employeeupdate",service.getEmployeeDetail(id));
    return "DailyReportSystem/employeeUpdate";
    }

    @PostMapping("/employeeUpdate/{id}")
    public String postEmployeeUpdate(@RequestParam(name="newpassword") String newpassword,
                                         @RequestParam(name="oldpassword") String oldpassword,Employee employee){
         LocalDateTime now=LocalDateTime.now();

         if(newpassword==("")) {
        employee.getAuthentication().setPassword(oldpassword);
         }else {
        employee.getAuthentication().setPassword(newpassword);
         }

        employee.setDeleteFlag(0);
        employee.setUpdateAt(now);
        employee.getAuthentication().setEmployee(employee);
        service.saveEmployee(employee);

        return "redirect:/employee/employeeUpdate/{id}/";
    }

    @GetMapping("/employeeDelete/{id}")
    public String getEmployeeDelete(@PathVariable("id") Integer id) {
        Employee employee=service.getEmployeeDetail(id);
        LocalDateTime now=LocalDateTime.now();

        employee.setDeleteFlag(1);
        employee.setUpdateAt(now);
        service.saveEmployee(employee);
        return "redirect:/employee/employeelist/";

    }


}