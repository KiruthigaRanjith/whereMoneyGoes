package com.spendy.spend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SpendsOfDayRequest {

    private String itemName;
    private BigDecimal cost;
    private LocalDate date;

    public SpendsOfDayRequest(String itemName, BigDecimal cost, LocalDate date) {
        this.itemName = itemName;
        this.cost = cost;
        this.date = date;
    }

    public SpendsOfDayRequest() {

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
