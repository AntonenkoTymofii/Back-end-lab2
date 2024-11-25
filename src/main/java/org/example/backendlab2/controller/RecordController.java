package org.example.backendlab2.controller;

import org.example.backendlab2.model.Record;
import org.example.backendlab2.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    // Створення запису
    @PostMapping
    public Record createRecord(@RequestParam Long userId, @RequestParam Long categoryId, @RequestParam BigDecimal amount) {
        try {
            return recordService.createRecord(userId, categoryId, amount);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    // Отримати запис за ID
    @GetMapping("/{recordId}")
    public Record getRecord(@PathVariable Long recordId) {
        return recordService.getRecord(recordId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found"));
    }

    // Отримати всі записи
    @GetMapping
    public List<Record> getAllRecords() {
        return recordService.getAllRecords();
    }

    // Видалити запис
    @DeleteMapping("/{recordId}")
    public void deleteRecord(@PathVariable Long recordId) {
        recordService.deleteRecord(recordId);
    }
}
