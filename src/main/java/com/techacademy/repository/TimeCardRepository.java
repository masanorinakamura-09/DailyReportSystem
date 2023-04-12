package com.techacademy.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techacademy.entity.TimeCard;

public interface TimeCardRepository extends JpaRepository<TimeCard, Integer> {
    TimeCard findByTimecardDateAndEmployeeId(LocalDate date,Integer id);
    List<TimeCard> findAllByTimecardDateOrderByEmployeeId(LocalDate date);
    Boolean existsByTimecardDateAndEmployeeId(LocalDate date,Integer id);

}
