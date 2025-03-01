package com.spendy.spend.repository;

import com.spendy.spend.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<ItemEntity,Long > {
    List<ItemEntity> findByItemNameIn(List<String> itemNames);

    boolean existsByItemName(String item);

    ItemEntity findByItemName(String item);

    List<Long> findIdByItemNameIn(List<String> items);
}
