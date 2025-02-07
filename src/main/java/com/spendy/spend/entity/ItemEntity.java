package com.spendy.spend.entity;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Table(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "item_name")
    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
