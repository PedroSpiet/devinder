package com.example.backcontainerusers.shared.adapters.infra.utils.tools;


import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Component
public class DateTools {

    public static int calcUserAge(Date date) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(DateTools.dateToLocalDate(date), currentDate).getYears();
    }

    public static Date ageToDate(int age) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, age * (-1));
        return calendar.getTime();
    }


    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
