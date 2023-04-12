package com.techacademy.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techacademy.entity.TimeCard;
import com.techacademy.repository.TimeCardRepository;

@Service
public class TimeCardService {
    private final TimeCardRepository repository;

    public TimeCardService(TimeCardRepository repository) {
        this.repository = repository;
    }

    public TimeCard setTimeCard(TimeCard timecard) {
        return repository.save(timecard);
    }

    public TimeCard getTimeCard(LocalDate date,Integer id) {
        return repository.findByTimecardDateAndEmployeeId(date, id);
    }

    public boolean existsWokrTime(LocalDate date,Integer id) {
        return repository.existsByTimecardDateAndEmployeeId(date,id);
    }

    public List<TimeCard> getTimeCardList(LocalDate date) {
        //return repository.findAll();//
        return repository.findAllByTimecardDateOrderByEmployeeId(date);
    }

}
