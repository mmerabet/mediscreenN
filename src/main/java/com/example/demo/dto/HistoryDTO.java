package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class HistoryDTO {
    private String idHistory;
    private String idConsultation;
    private String recommendations;
    private String observations;
    private LocalDate date;
}
