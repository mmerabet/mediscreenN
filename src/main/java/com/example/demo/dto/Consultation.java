package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Consultation {
    private String id;
    private String observations;
    private String recommendations;
    private LocalDate date;
}
