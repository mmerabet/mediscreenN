package com.example.demo.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DateTraitement {
    public int calculeAge(String dateString) {
        String[] dateSplit = dateString.split("-");
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        List<Integer> collect = Arrays.stream(dateSplit).map(Integer::parseInt).collect(Collectors.toList());

        LocalDate ld = LocalDate.of(collect.get(0), collect.get(1), collect.get(2));
//        System.out.println("ld: " + ld);
        LocalDate today = LocalDate.now();
//        System.out.println(today);
        Period age2 = Period.between(ld, today);
        System.out.println(age2.getYears());
        return Period.between(ld, today).getYears();
    }
}
