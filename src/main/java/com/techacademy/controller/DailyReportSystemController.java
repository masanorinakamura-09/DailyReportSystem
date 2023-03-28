package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.service.UserDetail;


@Controller
@RequestMapping()
public class DailyReportSystemController {
    @GetMapping("/")

        public String getTop(@AuthenticationPrincipal UserDetail userdetail,Model model){
        model.addAttribute("username",userdetail.getLoginName());
        model.addAttribute("userroll",userdetail.getUserRoll());
            return "topPage";
        }

}