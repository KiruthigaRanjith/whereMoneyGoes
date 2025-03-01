package com.spendy.spend.service;

import com.spendy.spend.entity.ItemEntity;

import java.util.List;

public interface ItemsService {
    List<String> getAllItems();

    ItemEntity getOrCreateItem(String item);

    List<Long> getItemsIdByName(List<String> items);
}
