package com.spendy.spend.serviceimpl;

import com.spendy.spend.entity.ItemEntity;
import com.spendy.spend.repository.ItemsRepository;
import com.spendy.spend.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsRepository itemsRepository;

    @Override
    public List<String> getAllItems() {
        List<ItemEntity> itemEntities = itemsRepository.findAll();
        if(!itemEntities.isEmpty())
           return itemEntities.stream().map(ItemEntity::getItemName).collect(Collectors.toList());
        return List.of();
    }
}
