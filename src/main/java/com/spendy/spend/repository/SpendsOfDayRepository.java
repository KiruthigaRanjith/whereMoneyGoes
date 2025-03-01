package com.spendy.spend.repository;

import com.spendy.spend.entity.SpendsOfDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SpendsOfDayRepository extends JpaRepository<SpendsOfDay, Long> {

    List<SpendsOfDay> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

    List<SpendsOfDay> findAllByDateBetweenAndItemEntityIdIn(LocalDate startDate, LocalDate endDate, List<Long> itemIds);
}
