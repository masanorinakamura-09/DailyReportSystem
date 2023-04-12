package com.techacademy.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techacademy.entity.TimeCard;

public interface TimeCardRepository extends JpaRepository<TimeCard, Integer> {
    TimeCard findByTimecardDateAndEmployeeId(LocalDate date,Integer id);
    Boolean existsByTimecardDateAndEmployeeId(LocalDate date,Integer id);

}
