package com.spendy.spend.util;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DateUtil {

    private static Map<String, LocalDate> dateMap = new HashMap<>();

    public static Map<String, LocalDate> getDateRange(LocalDate startDate, LocalDate endDate){
        if(Objects.isNull(startDate))
            startDate = LocalDate.now().withDayOfMonth(1);
        if(Objects.isNull(endDate))
            endDate = LocalDate.now();

        dateMap.put("startDate", startDate);
        dateMap.put("endDate", endDate);

        return dateMap;
    }

}
