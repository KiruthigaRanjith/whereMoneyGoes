package com.spendy.spend.controller;

import com.spendy.spend.dto.SpendsOfDayRequest;
import com.spendy.spend.dto.TotalSpend;
import com.spendy.spend.entity.SpendsOfDay;
import com.spendy.spend.service.SpendCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/spend")
public class SpendController {

    @Autowired
    private SpendCalculator spendCalculator;

    @PostMapping("/total-spend")
    public ResponseEntity<TotalSpend> getTotalSpendForDateRange(@RequestParam(required = false) LocalDate startDate,
                                                               @RequestParam(required = false) LocalDate endDate,
                                                               @RequestBody List<String> items){
        TotalSpend totalSpend = spendCalculator.getTotalSpendForDateRange(startDate, endDate, items);
        return new ResponseEntity<>(totalSpend, HttpStatus.OK);
    }

    @PostMapping("/all-spends")
    public ResponseEntity<List<SpendsOfDayRequest>> getAllSpendsForDateRange(@RequestParam(required = false) LocalDate startDate,
                                                               @RequestParam(required = false) LocalDate endDate,
                                                               @RequestBody List<String> items){
        List<SpendsOfDayRequest> spendsOfDayRequest = spendCalculator.getAllSpendsForDateRange(startDate, endDate, items);
        return new ResponseEntity<>(spendsOfDayRequest, HttpStatus.OK);
    }

    @PostMapping("/save-spend")
    public ResponseEntity<List<SpendsOfDay>> saveSpendsOfDay(@RequestBody List<SpendsOfDayRequest> spendsOfDayRequests){
        return new ResponseEntity<>(spendCalculator.saveSpendsOfDay(spendsOfDayRequests), HttpStatus.OK);

    }
}
