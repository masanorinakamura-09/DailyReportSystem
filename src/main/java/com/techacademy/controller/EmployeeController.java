package com.techacademy.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Authentication.Register;
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
    @GetMapping("/employeedetail/{id}")
    public String getEmployeeDetail(@PathVariable("id") Integer id,Model model) {

        model.addAttribute("employeedetail",service.getEmployeeDetail(id));
        return "DailyReportSystem/employeedetail";
    }


    @GetMapping("/employeeregister")
    public String getEmployeeRegister(@ModelAttribute Employee employee){

        return "DailyReportSystem/employeeregister";
    }

    @PostMapping("/employeeregister")
    public String postEmployeeRegister(@Validated({Register.class}) Employee employee,BindingResult res){
        LocalDateTime now=LocalDateTime.now();

        employee.setDeleteFlag(0);
        employee.setCreatedAt(now);
        employee.setUpdateAt(now);
        employee.getAuthentication().setEmployee(employee);

        if(res.hasErrors()) {
            return getEmployeeRegister(employee);
        }

        service.saveEmployee(employee);

        return "redirect:/employee/employeeregister";
    }

    @GetMapping("/employeeupdate/{id}")
    public String getEmployeeUpdate(@PathVariable("id") Integer id,Model model) {
        if(id!=null) {
            model.addAttribute("employee",service.getEmployeeDetail(id));
        }
    return "DailyReportSystem/employeeupdate";
    }

    @PostMapping("/employeeupdate/{id}")
    public String postEmployeeUpdate(@RequestParam(name="password") String password,
            @Validated Employee employee,BindingResult res,Model model){

        if(res.hasErrors()) {
            model.addAttribute("employee",employee);
            return getEmployeeUpdate(null,model);
        }

        LocalDateTime now=LocalDateTime.now();

         if(password==("")) {
        Employee oldemployee=service.getEmployeeDetail(employee.getId());
        password=oldemployee.getAuthentication().getPassword();
         }

        employee.getAuthentication().setPassword(password);
        employee.setDeleteFlag(0);
        employee.setUpdateAt(now);
        employee.getAuthentication().setEmployee(employee);



        service.saveEmployee(employee);

        return "redirect:/employee/employeeupdate/{id}/";
    }

    @GetMapping("/employeedelete/{id}")
    public String getEmployeeDelete(@PathVariable("id") Integer id) {
        Employee employee=service.getEmployeeDetail(id);
        LocalDateTime now=LocalDateTime.now();

        employee.setDeleteFlag(1);
        employee.setUpdateAt(now);
        service.saveEmployee(employee);
        return "redirect:/employee/employeelist/";

    }


}