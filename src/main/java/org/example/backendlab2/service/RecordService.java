package org.example.backendlab2.service;

import org.example.backendlab2.model.Record;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class RecordService {
    private Map<Long, Record> records = new HashMap<>();
    private AtomicLong recordIdCounter = new AtomicLong();

    public Record createRecord(Long userId, Long categoryId, BigDecimal amount) {
        Long id = recordIdCounter.incrementAndGet();
        Record record = new Record(id, userId, categoryId, LocalDateTime.now(), amount);
        records.put(id, record);
        return record;
    }

    public Optional<Record> getRecord(Long id) {
        return Optional.ofNullable(records.get(id));
    }

    public void deleteRecord(Long id) {
        records.remove(id);
    }

    public List<Record> getRecordsByUserAndCategory(Long userId, Long categoryId) {
        return records.values().stream()
                .filter(record -> (userId == null || record.getUserId().equals(userId)) &&
                        (categoryId == null || record.getCategoryId().equals(categoryId)))
                .collect(Collectors.toList());
    }
}
