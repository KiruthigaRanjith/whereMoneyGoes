package com.spendy.spend.serviceimpl;

import com.spendy.spend.converter.SpendsOfDayConverter;
import com.spendy.spend.dto.SpendsOfDayRequest;
import com.spendy.spend.dto.TotalSpend;
import com.spendy.spend.entity.SpendsOfDay;
import com.spendy.spend.repository.SpendsOfDayRepository;
import com.spendy.spend.service.ItemsService;
import com.spendy.spend.service.SpendCalculator;
import com.spendy.spend.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SpendCalculatorImpl implements SpendCalculator {

    @Autowired
    private SpendsOfDayRepository spendsOfDayRepository;

    @Autowired
    private SpendsOfDayConverter spendsOfDayConverter;

    @Autowired
    private ItemsService itemsService;

    @Override
    public List<SpendsOfDay> saveSpendsOfDay(List<SpendsOfDayRequest> spendsOfDayRequests){

        Set<SpendsOfDay> spendsOfDays = spendsOfDayRequests.stream().map(spendsOfDayConverter::convertToSpendsOfDay)
                .collect(Collectors.toSet());
        return spendsOfDayRepository.saveAll(spendsOfDays);

    }

    @Override
    public TotalSpend getTotalSpendForDateRange(LocalDate startDate, LocalDate endDate, List<String> items) {

        TotalSpend totalSpend = new TotalSpend();
        Map<String,LocalDate> dateMap = DateUtil.getDateRange(startDate, endDate);
        startDate = dateMap.get("startDate");
        endDate = dateMap.get("endDate");

        List<SpendsOfDay> spendsOfDays;

        if(Objects.isNull(items) || items.isEmpty()) {
            spendsOfDays = spendsOfDayRepository.findAllByDateBetween(startDate, endDate);
        } else {
            List<Long> itemIds = itemsService.getItemsIdByName(items);
            spendsOfDays = spendsOfDayRepository.findAllByDateBetweenAndItemEntityIdIn(startDate, endDate, itemIds);
        }

        
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
        return totalSpend;
    }

    @Override
    public List<SpendsOfDayRequest> getAllSpendsForDateRange(LocalDate startDate, LocalDate endDate, List<String> items) {
        Map<String,LocalDate> dateMap = DateUtil.getDateRange(startDate, endDate);
        startDate = dateMap.get("startDate");
        endDate = dateMap.get("endDate");

        List<SpendsOfDay> spendsOfDays;

        if(Objects.isNull(items) || items.isEmpty()) {
            spendsOfDays = spendsOfDayRepository.findAllByDateBetween(startDate, endDate);
        } else {
            List<Long> itemIds = itemsService.getItemsIdByName(items);
            spendsOfDays = spendsOfDayRepository.findAllByDateBetweenAndItemEntityIdIn(startDate, endDate, itemIds);
        }

        return spendsOfDays.stream().map(SpendsOfDayConverter::convertToSpendsOfDay).collect(Collectors.toList());

    }
}
