package com.spendy.spend.converter;

import com.spendy.spend.dto.SpendsOfDayRequest;
import com.spendy.spend.entity.ItemEntity;
import com.spendy.spend.entity.SpendsOfDay;
import com.spendy.spend.service.ItemsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpendsOfDayConverter {

    private static final Logger log = LoggerFactory.getLogger(SpendsOfDayConverter.class);
    @Autowired
    private ItemsService itemsService;

    public SpendsOfDay convertToSpendsOfDay(SpendsOfDayRequest spendsOfDayRequest){
        log.info("Converting SpendsOfDayRequest : {}",spendsOfDayRequest.toString());
        SpendsOfDay spendsOfDay = new SpendsOfDay();
        spendsOfDay.setCost(spendsOfDayRequest.getCost());
        spendsOfDay.setDate(spendsOfDayRequest.getDate());

        ItemEntity itemEntity = itemsService.getOrCreateItem(spendsOfDayRequest.getItemName());

        spendsOfDay.setItemEntity(itemEntity);

        return spendsOfDay;
    }

    public static SpendsOfDayRequest convertToSpendsOfDay(SpendsOfDay spendsOfDay){
        log.info("Converting spendsOfDay : {}",spendsOfDay.toString());
        SpendsOfDayRequest spendsOfDayRequest = new SpendsOfDayRequest();
        spendsOfDayRequest.setCost(spendsOfDay.getCost());
        spendsOfDayRequest.setDate(spendsOfDay.getDate());

        spendsOfDayRequest.setItemName(spendsOfDay.getItemEntity().getItemName());

        return spendsOfDayRequest;
    }

}
