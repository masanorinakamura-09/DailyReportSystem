package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.FollowList;
import com.techacademy.service.EmployeeService;
import com.techacademy.service.FollowService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("/follow")
public class FollowController {
    private final FollowService service;
    private final EmployeeService employeeservice;

    public FollowController(FollowService service, EmployeeService employeeservice) {
        this.service = service;
        this.employeeservice = employeeservice;
    }

    @GetMapping("/saveFollower/{id}")
    public String saveFollower(@PathVariable("id") Integer id,
            @AuthenticationPrincipal UserDetail userdetail,Model model,FollowList followlist) {


        followlist.setEmployee(userdetail.getEmployee());
        followlist.setFollower(employeeservice.getEmployeeDetail(id));
        if(service.exitsFollowList(userdetail.getUserId(),id)){
        return "redirect:/report/reportdetail/{id}/";
        }
        service.saveFollowList(followlist);
        return "redirect:/report/reportdetail/{id}/";
    }

    @GetMapping("/cancelFollower/{id}")
    public String cancelFollower(@PathVariable("id") Integer id,
            @AuthenticationPrincipal UserDetail userdetail,Model model,FollowList followlist) {
        followlist=service.getfollowlist(userdetail.getUserId(), id);
        service.cancelFollower(followlist.getId());

                return "redirect:/report/reportdetail/{id}/";

    }


}
