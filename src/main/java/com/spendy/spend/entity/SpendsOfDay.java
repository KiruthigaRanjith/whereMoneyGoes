package com.spendy.spend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "spends_of_day")
public class SpendsOfDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private ItemEntity itemEntity;

    @Column
    private BigDecimal cost;

    @Column
    private LocalDate date;

    public long getId() {
        return id;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
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