package com.techacademy.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("report")
public class ReportController {
    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping("/reportlist")
    public String getReportList(@AuthenticationPrincipal UserDetail userdetail,Model model) {

        model.addAttribute("reportlist", service.getReportList());
        model.addAttribute("index", service.getIndex());

        model.addAttribute("username",userdetail.getLoginName());
        model.addAttribute("userroll",userdetail.getUserRoll());

        return "DailyReportSystem/reportList";

    }

     @GetMapping("/reportdetail/{id}")
        public String getReportDetail(@PathVariable("id") Integer id,@AuthenticationPrincipal UserDetail userdetail,Model model) {

            model.addAttribute("report",service.getReport(id));

            model.addAttribute("userid", userdetail.getUserId());
            model.addAttribute("username",userdetail.getLoginName());
            model.addAttribute("userroll",userdetail.getUserRoll());
            return "DailyReportSystem/reportDetail";
        }

    @GetMapping("/reportregister")
    public String getReportReigister(Report report,@AuthenticationPrincipal UserDetail userdetail,Model model) {
        LocalDate localDate=LocalDate.now();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date=localDate.format(dateTimeFormatter);

        model.addAttribute("report",report);
        model.addAttribute("currentdate",date);

        model.addAttribute("username",userdetail.getLoginName());
        model.addAttribute("userroll",userdetail.getUserRoll());

        return "DailyReportSystem/reportRegister";

    }

    @PostMapping("/reportregister")
    public String postReportRegister(@RequestParam(name="content") String content,@RequestParam(name="reportDate") String reportDate,
            @Validated Report report,BindingResult res,
            @AuthenticationPrincipal UserDetail userdetail,Model model) {

        LocalDate localDate=LocalDate.parse(reportDate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime now=LocalDateTime.now();
        Employee employee=userdetail.getEmployee();

        if(res.hasErrors()) {
            return getReportReigister(report,userdetail,model);
        }


        report.setContent(content);
        report.setReportDate(localDate);
        report.setEmployee(employee);
        report.setCreatedAt(now);
        report.setUpdatedAt(now);

        service.saveReport(report);

        return "redirect:/report/reportlist";

    }

    @GetMapping("/reportupdate/{id}")
    public String getReportUpdate(@PathVariable("id") Integer id,@AuthenticationPrincipal UserDetail userdetail,Model model) {

        if(id!=null) {
        Report report=service.getReport(id);

        LocalDate localDate=report.getReportDate();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date=localDate.format(dateTimeFormatter);


        model.addAttribute("report",report);
        model.addAttribute("reportdate", date);
        }

        model.addAttribute("username",userdetail.getLoginName());
        model.addAttribute("userroll",userdetail.getUserRoll());

        return "DailyReportSystem/reportUpdate";

    }

    @PostMapping("/reportupdate/{id}")
    public String postReportUpdate(@RequestParam(name="content") String content,@RequestParam(name="reportDate") String reportDate,
            @Validated Report report,BindingResult res,
            @AuthenticationPrincipal UserDetail userdetail,Model model) {

        if(res.hasErrors()) {
            model.addAttribute("report", report);
            model.addAttribute("reportdate", reportDate);
            return getReportUpdate(null,userdetail,model);
        }

        LocalDate localDate=LocalDate.parse(reportDate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime now=LocalDateTime.now();

        Employee employee=userdetail.getEmployee();


        report.setContent(content);
        report.setReportDate(localDate);
        report.setEmployee(employee);
        report.setUpdatedAt(now);

        service.saveReport(report);

        return "redirect:/report/reportlist";

    }
}
