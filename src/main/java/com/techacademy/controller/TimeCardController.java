package com.techacademy.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        /*if(timecard.getTimecardDate()!=null) {

        }*/
        model.addAttribute("timecard",timecard);
        model.addAttribute("username",userdetail.getLoginName());
        model.addAttribute("userroll",userdetail.getUserRoll());
        return "DailyReportSystem/timeCard";
    }

    @PostMapping("/worktime")
    public String postWorkTime(TimeCard timecard,@AuthenticationPrincipal UserDetail userdetail,Model model) {

        Integer id=userdetail.getUserId();
        LocalDate now=LocalDate.now();
        timecard.setTimecardDate(now);

        if(service.existsWokrTime(now,id )) {
            return GetTimeCardTop(timecard,userdetail,model);
        }

        timecard.setEmployee(userdetail.getEmployee());
        timecard.setWorkTime(LocalTime.now());
        service.setTimeCard(timecard);
        return "redirect:/timecard/";
    }

    @PostMapping("/leavingworktime")
    public String postLeavingworktime(TimeCard timecard,@AuthenticationPrincipal UserDetail userdetail,Model model) {

        Integer id=userdetail.getUserId();
        LocalDate date=LocalDate.now();
        LocalTime time=LocalTime.now();

        if(!service.existsWokrTime(date,id)) {
            timecard.setLeavingworkTime(time);
            return GetTimeCardTop(timecard,userdetail,model);
        }

        timecard=service.getTimeCard(date, id);

        if(timecard.getLeavingworkTime()!=null) {
            timecard.setLeavingworkTime(time);
            return GetTimeCardTop(timecard,userdetail,model);
        }
        timecard.setLeavingworkTime(time);
        service.setTimeCard(timecard);
        return "redirect:/timecard/";

    }
    @GetMapping("/timecardlist")
    public String getTimecardList(@AuthenticationPrincipal UserDetail userdetail,Model model,LocalDate date) {

        LocalDate nowDate=LocalDate.now();
        if(date==null)date=nowDate;

        model.addAttribute("timecardlist",service.getTimeCardList(date));
        model.addAttribute("date",date);
        model.addAttribute("nowDate",nowDate);

        model.addAttribute("username",userdetail.getLoginName());
        model.addAttribute("userroll",userdetail.getUserRoll());

        return "DailyReportSystem/timecardList";

    }

    @PostMapping("/timecardlist")
    public String postTimecardList(@AuthenticationPrincipal UserDetail userdetail,Model model,@RequestParam(name="Date") String date) {
        LocalDate localDate=LocalDate.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        getTimecardList(userdetail,model,localDate);

        return "DailyReportSystem/timecardList";
    }

    @GetMapping("/timecardupdate/{id}")
    public String getTimeCardUpdate(@PathVariable("id") Integer id,
                                    @AuthenticationPrincipal UserDetail userdetail,
                                    Model model) {
        model.addAttribute("timecard",service.getTimeCard(id));
        model.addAttribute("Date",service.getTimeCard(id).getTimecardDate());

        model.addAttribute("username",userdetail.getLoginName());
        model.addAttribute("userroll",userdetail.getUserRoll());
        return "DailyReportSystem/timecardUpdate";

    }

    @PostMapping("/timecardupdate/{id}")
    public String postTimeCardUpdate(@PathVariable("id") Integer id,
                                    @AuthenticationPrincipal UserDetail userdetail,
                                    TimeCard timecard,
                                    Model model){


            timecard.setEmployee(service.getTimeCard(id).getEmployee());
            service.setTimeCard(timecard);
            return "redirect:/timecard/timecardlist";

    }
}
