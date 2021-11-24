package com.example.demo.model;

import com.example.demo.dto.Consultation;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class History {
    @Id
    private String id;
    private List<Consultation> consultations;
}
