package com.techacademy.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Authentication.Register;
import com.techacademy.service.AuthenticationService;
import com.techacademy.service.EmployeeService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;
    private final AuthenticationService authenticationservice;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public EmployeeController(EmployeeService service,AuthenticationService authenticationservice) {
        this.service = service;
        this.authenticationservice=authenticationservice;
    }

    @GetMapping("/employeelist")
    public String getEmployeeList(@AuthenticationPrincipal UserDetail userdetail,Model model) {

        model.addAttribute("employeelist",service.getEmployeeList());
        model.addAttribute("index",service.getIndex());
        model.addAttribute("username",userdetail.getLoginName());
        model.addAttribute("userroll",userdetail.getUserRoll());

        return "DailyReportSystem/employeeList";
    }
    @GetMapping("/employeedetail/{id}")
    public String getEmployeeDetail(@PathVariable("id") Integer id,@AuthenticationPrincipal UserDetail userdetail,Model model) {

        model.addAttribute("employeedetail",service.getEmployeeDetail(id));
        model.addAttribute("username",userdetail.getLoginName());
        model.addAttribute("userroll",userdetail.getUserRoll());
        return "DailyReportSystem/employeedetail";
    }


    @GetMapping("/employeeregister")
    public String getEmployeeRegister(Employee employee,@AuthenticationPrincipal UserDetail userdetail,Model model){
        model.addAttribute("employee",employee);
        model.addAttribute("username",userdetail.getLoginName());
        model.addAttribute("userroll",userdetail.getUserRoll());
        return "DailyReportSystem/employeeregister";
    }

    @PostMapping("/employeeregister")
    public String postEmployeeRegister(@Validated({Register.class}) Employee employee,BindingResult res,@AuthenticationPrincipal UserDetail userdetail,Model model){
        LocalDateTime now=LocalDateTime.now();

        if(authenticationservice.existsCode(employee.getAuthentication().getCode())) {
            return getEmployeeRegister(employee,userdetail,model);
        }

        if(res.hasErrors()) {
            employee.setName(null);
            employee.getAuthentication().setCode(null);
            return getEmployeeRegister(employee,userdetail,model);
        }

        String password=employee.getAuthentication().getPassword();
        employee.getAuthentication().setPassword(passwordEncoder.encode(password));

        employee.setDeleteFlag(0);
        employee.setCreatedAt(now);
        employee.setUpdatedAt(now);
        employee.getAuthentication().setEmployee(employee);



        service.saveEmployee(employee);

        return "redirect:/employee/employeelist";
    }

    @GetMapping("/employeeupdate/{id}")
    public String getEmployeeUpdate(@PathVariable("id") Integer id,@AuthenticationPrincipal UserDetail userdetail,Model model) {
        if(id!=null) {
            model.addAttribute("employee",service.getEmployeeDetail(id));
        }
        model.addAttribute("username",userdetail.getLoginName());
        model.addAttribute("userroll",userdetail.getUserRoll());
    return "DailyReportSystem/employeeupdate";
    }

    @PostMapping("/employeeupdate/{id}")
    public String postEmployeeUpdate(@RequestParam(name="password") String password,
            @Validated Employee employee,BindingResult res,@AuthenticationPrincipal UserDetail userdetail,Model model){

        if(res.hasErrors()) {
            model.addAttribute("employee",employee);
            return getEmployeeUpdate(null,userdetail,model);
        }

        LocalDateTime now=LocalDateTime.now();

         if(password==("")) {
        Employee oldemployee=service.getEmployeeDetail(employee.getId());
        password=oldemployee.getAuthentication().getPassword();
         }else {
             password=passwordEncoder.encode(password);
         }

        employee.getAuthentication().setPassword(password);
        employee.setDeleteFlag(0);
        employee.setUpdatedAt(now);
        employee.getAuthentication().setEmployee(employee);



        service.saveEmployee(employee);

        return "redirect:/employee/employeeupdate/{id}/";
    }

    @GetMapping("/employeedelete/{id}")
    public String getEmployeeDelete(@PathVariable("id") Integer id) {
        Employee employee=service.getEmployeeDetail(id);
        LocalDateTime now=LocalDateTime.now();

        employee.setDeleteFlag(1);
        employee.setUpdatedAt(now);
        service.saveEmployee(employee);
        return "redirect:/employee/employeelist/";

    }


}