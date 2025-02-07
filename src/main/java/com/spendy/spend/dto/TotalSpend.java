package com.spendy.spend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TotalSpend {

    private BigDecimal totalSpend;
    private Map<String,BigDecimal> itemsAndSpend;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal maximumSpentAmount;
    private List<String> maximumAmtSpentItem;

    public Map<String, BigDecimal> getItemsAndSpend() {
        return itemsAndSpend;
    }

    public void setItemsAndSpend(Map<String, BigDecimal> itemsAndSpend) {
        this.itemsAndSpend = itemsAndSpend;
    }

    public BigDecimal getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(BigDecimal totalSpend) {
        this.totalSpend = totalSpend;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getMaximumSpentAmount() {
        return maximumSpentAmount;
    }

    public void setMaximumSpentAmount(BigDecimal maximumSpentAmount) {
        this.maximumSpentAmount = maximumSpentAmount;
    }

    public List<String> getMaximumAmtSpentItem() {
        return maximumAmtSpentItem;
    }

    public void setMaximumAmtSpentItem(List<String> maximumAmtSpentItem) {
        this.maximumAmtSpentItem = maximumAmtSpentItem;
    }
}
