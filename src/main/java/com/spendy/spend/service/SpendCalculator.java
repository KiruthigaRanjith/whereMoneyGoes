package com.spendy.spend.service;

import com.spendy.spend.dto.SpendsOfDayRequest;
import com.spendy.spend.dto.TotalSpend;
import com.spendy.spend.entity.SpendsOfDay;

import java.time.LocalDate;
import java.util.List;

public interface SpendCalculator {

    List<SpendsOfDay> saveSpendsOfDay(List<SpendsOfDayRequest> spendsOfDayRequests);

    public TotalSpend getTotalSpendForDateRange(LocalDate startDate, LocalDate endDate, List<String> items);

    List<SpendsOfDayRequest> getAllSpendsForDateRange(LocalDate startDate, LocalDate endDate, List<String> items);
}
