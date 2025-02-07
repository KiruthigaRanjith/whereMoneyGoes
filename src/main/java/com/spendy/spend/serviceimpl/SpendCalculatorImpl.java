package com.spendy.spend.serviceimpl;

import com.spendy.spend.dto.TotalSpend;
import com.spendy.spend.entity.SpendsOfDay;
import com.spendy.spend.repository.SpendsOfDayRepository;
import com.spendy.spend.service.SpendCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SpendCalculatorImpl implements SpendCalculator {

    @Autowired
    private SpendsOfDayRepository spendsOfDayRepository;

    @Override
    public TotalSpend getAllSpendsForDateRange(LocalDate startDate, LocalDate endDate, List<String> items) {

        TotalSpend totalSpend = new TotalSpend();
        if(Objects.isNull(startDate))
            startDate = LocalDate.now().withDayOfMonth(1);
        if(Objects.isNull(endDate))
            endDate = LocalDate.now();

        List<SpendsOfDay> spendsOfDays = spendsOfDayRepository.findAllByDateBetween(startDate, endDate);
        if(Objects.nonNull(spendsOfDays) && !spendsOfDays.isEmpty()){
            totalSpend.setStartDate(startDate);
            totalSpend.setEndDate(endDate);
            totalSpend.setTotalSpend(spendsOfDays.stream().map(SpendsOfDay::getCost).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
            Map<String, BigDecimal> costPerItemName = spendsOfDays.stream()
                    .collect(Collectors.groupingBy(
                            spend -> spend.getItemEntity().getItemName(),
                            Collectors.reducing(BigDecimal.ZERO, SpendsOfDay::getCost, BigDecimal::add)
                    ));
            totalSpend.setItemsAndSpend(costPerItemName);

            BigDecimal maxCost = costPerItemName.values().stream()
                    .max(BigDecimal::compareTo)
                    .orElse(BigDecimal.ZERO);

            totalSpend.setMaximumSpentAmount(maxCost);
            totalSpend.setMaximumAmtSpentItem(costPerItemName.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(maxCost))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList()));

        }
        return null;
    }
}
