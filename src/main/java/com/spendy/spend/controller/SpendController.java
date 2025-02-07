package com.spendy.spend.controller;

import com.spendy.spend.dto.TotalSpend;
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

    @GetMapping("/total-spend")
    public ResponseEntity<TotalSpend> getAllSpendsForDateRange(@RequestParam(required = false) LocalDate startDate,
                                                               @RequestParam(required = false) LocalDate endDate,
                                                               @RequestBody List<String> items){
        TotalSpend totalSpend = spendCalculator.getAllSpendsForDateRange(startDate, endDate, items);
        return new ResponseEntity<>(totalSpend, HttpStatus.OK);
    }
}
