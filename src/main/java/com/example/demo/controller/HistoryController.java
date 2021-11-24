package com.example.demo.controller;

import com.example.demo.dto.HistoryDTO;
import com.example.demo.model.History;
import com.example.demo.service.HistoryService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Data
@Slf4j
@RestController
@RequestMapping("/patHistory")
@OpenAPIDefinition(info = @Info(
        title = "history controller",
        version = "1.0",
        description = "Availables methods for Historys"
))
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("historys")
    public ResponseEntity<List<History>> getAllHistorys() {
        log.info("Controller getAllHistorys");
        List<History> historyList = this.historyService.findAllHistory();
        return new ResponseEntity<>(historyList, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<History> getHistoryById(@RequestParam String id) {
        log.info("Controller getHistoryById");
        History History = this.historyService.findHistoryById(id);
        return new ResponseEntity<>(History, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public ResponseEntity<History> addHistory(@RequestBody HistoryDTO historyDTO) {
        log.info("Controller addHistory");
        History newHistory = this.historyService.addHistory(historyDTO);
        return new ResponseEntity<>(newHistory, HttpStatus.CREATED);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("")
    public ResponseEntity<History> updateHistory(@RequestBody HistoryDTO historyDTO) {
        log.info("Controller updateHistory");
        History updateHistory = this.historyService.updateHistory(historyDTO);
        return new ResponseEntity<>(updateHistory, HttpStatus.OK);
    }

    @DeleteMapping("/{idHistory}")
    public ResponseEntity<?> deleteHistory(@PathVariable String idHistory, @RequestParam String idConsultation) {
        log.info("Controller deleteHistory");
        this.historyService.deleteHistory(idHistory, idConsultation);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
