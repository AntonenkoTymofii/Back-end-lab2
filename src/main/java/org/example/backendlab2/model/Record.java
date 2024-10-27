package org.example.backendlab2.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Record {
    private Long id;
    private Long userId;
    private Long categoryId;
    private LocalDateTime dateTime;
    private BigDecimal amount;

    public Record(Long id, Long userId, Long categoryId,
                  LocalDateTime dateTime, BigDecimal amount) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.dateTime = dateTime;
        this.amount = amount;
    }
}
