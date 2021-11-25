package com.example.demo.service;

import com.example.demo.dto.Consultation;
import com.example.demo.dto.HistoryDTO;
import com.example.demo.exception.HistoryNotFoundException;
import com.example.demo.model.History;
import com.example.demo.repo.HistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<History> findAllHistory() {
        return historyRepository.findAll();
    }

    public History addHistory(HistoryDTO historyDTO) {
        Consultation newConsultation = new Consultation();
        newConsultation.setId(UUID.randomUUID().toString());
        newConsultation.setDate(LocalDate.now());
        newConsultation.setRecommendations(historyDTO.getRecommendations());
        newConsultation.setObservations(historyDTO.getObservations());
        if (historyRepository.existsById(historyDTO.getIdHistory())) {
            History history1 = this.findHistoryById(historyDTO.getIdHistory());
            history1.getConsultations().add(newConsultation);
            return this.historyRepository.save(history1);
        } else {
            History newHistory = new History();
            List<Consultation> consultations = new ArrayList<>();
            consultations.add(newConsultation);
            newHistory.setId(historyDTO.getIdHistory());
            newHistory.setConsultations(consultations);
            return this.historyRepository.save(newHistory);
        }
    }


    public History findHistoryById(String idHistory) {
        return this.historyRepository.findById(idHistory)
                .orElseThrow(() -> new HistoryNotFoundException("History by id " + idHistory + " was not found"));
    }

    public void deleteHistory(String idHistory, String idConsultation) {
        History history = this.findHistoryById(idHistory);
        List<Consultation> consultations = history.getConsultations()
                .stream()
                .filter(consultation -> !Objects.equals(idConsultation, consultation.getId()))
                .collect(Collectors.toList());
        history.setConsultations(consultations);
        this.historyRepository.save(history);
    }

    public History updateHistory(HistoryDTO historyDTO) {
        if (historyRepository.existsById(historyDTO.getIdHistory())) {
            History history = this.findHistoryById(historyDTO.getIdHistory());
            Consultation consultation1 = history.getConsultations()
                    .stream()
                    .filter(consultation -> historyDTO.getIdConsultation().equals(consultation.getId()))
                    .findAny().get();

            List<Consultation> newConsultations = history.getConsultations().stream()
                    .filter(consultation -> consultation.getId().equals(historyDTO.getIdConsultation()))
                    .collect(Collectors.toList());

            consultation1.setDate(historyDTO.getDate());
            consultation1.setDate(LocalDate.now());
            consultation1.setRecommendations(historyDTO.getRecommendations());
            consultation1.setObservations(historyDTO.getObservations());
            newConsultations.add(consultation1);
            history.setConsultations(newConsultations);
            return this.historyRepository.save(history);
        } else {
            throw new HistoryNotFoundException(" ID history or ID consultation not found ");
        }

    }
}
