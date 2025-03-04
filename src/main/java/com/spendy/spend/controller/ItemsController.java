package com.spendy.spend.controller;

import com.spendy.spend.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllItems(){
        return new ResponseEntity<>(itemsService.getAllItems(), HttpStatus.OK);
    }
}
