package com.spendy.spend.repository;

import com.spendy.spend.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<ItemEntity,Long > {
}
