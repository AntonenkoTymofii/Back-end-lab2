package org.example.backendlab2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "date")
    private LocalDateTime dateTime;

    @Column(name = "amount")
    private BigDecimal amount;

    public Record(Long id, Person person, Category category,
                  LocalDateTime dateTime, BigDecimal amount) {
        this.id = id;
        this.person = person;
        this.category = category;
        this.dateTime = dateTime;
        this.amount = amount;
    }

    public Record() {
    }
}
