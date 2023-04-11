package com.techacademy.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.TimeCard;
import com.techacademy.service.TimeCardService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("timecard")
public class TimeCardController {
    private final TimeCardService service;

    public TimeCardController(TimeCardService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String GetTimeCardTop(TimeCard timecard,@AuthenticationPrincipal UserDetail userdetail,Model model) {
        model.addAttribute("timecard",timecard);
        model.addAttribute("username",userdetail.getLoginName());
        model.addAttribute("userroll",userdetail.getUserRoll());
        return "DailyReportSystem/timeCard";
    }

    @PostMapping("/worktime")
    public String postWorkTime(TimeCard timecard,@AuthenticationPrincipal UserDetail userdetail) {

        timecard.setEmployee(userdetail.getEmployee());
        timecard.setTimecardDate(LocalDate.now());
        timecard.setWorkTime(LocalTime.now());
        service.setTimeCard(timecard);
        return "redirect:/timecard/";
    }

    @PostMapping("/leavingworktime")
    public String postLeavingworktime(TimeCard timecard,@AuthenticationPrincipal UserDetail userdetail) {

        timecard=service.getTimeCard(LocalDate.now(), userdetail.getUserId());
        /*timecard.setEmployee(userdetail.getEmployee());
        timecard.setTimecardDate(LocalDate.now());*/
        timecard.setLeavingworkTime(LocalTime.now());
        service.setTimeCard(timecard);
        return "redirect:/timecard/";
    }
}
