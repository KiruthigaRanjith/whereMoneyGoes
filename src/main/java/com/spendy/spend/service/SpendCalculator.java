package com.spendy.spend.service;

import com.spendy.spend.dto.TotalSpend;

import java.time.LocalDate;
import java.util.List;

public interface SpendCalculator {

    public TotalSpend getAllSpendsForDateRange(LocalDate startDate, LocalDate endDate, List<String> items);
}
