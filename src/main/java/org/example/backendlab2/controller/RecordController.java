package org.example.backendlab2.controller;

import org.example.backendlab2.model.Record;
import org.example.backendlab2.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired private RecordService recordService;

    @PostMapping
    public Record createRecord(@RequestParam Long userId, @RequestParam Long categoryId, @RequestParam BigDecimal amount) {
        return recordService.createRecord(userId, categoryId, amount);
    }

    @GetMapping("/{recordId}")
    public Record getRecord(@PathVariable Long recordId) {
        return recordService.getRecord(recordId).orElseThrow(() -> new RuntimeException("Record not found"));
    }

    @DeleteMapping("/{recordId}")
    public void deleteRecord(@PathVariable Long recordId) {
        recordService.deleteRecord(recordId);
    }

    @GetMapping
    public List<Record> getRecords(@RequestParam(required = false) Long userId, @RequestParam(required = false) Long categoryId) {
        if (userId == null && categoryId == null) {
            throw new RuntimeException("Parameters userId or categoryId are required");
        }
        return recordService.getRecordsByUserAndCategory(userId, categoryId);
    }
}