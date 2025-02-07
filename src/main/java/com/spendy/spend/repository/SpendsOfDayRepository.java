package com.spendy.spend.repository;

import com.spendy.spend.entity.SpendsOfDay;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SpendsOfDayRepository {

    List<SpendsOfDay> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
}
