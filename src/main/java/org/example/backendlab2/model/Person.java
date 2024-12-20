package org.example.backendlab2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "author")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Record> records;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Account account;

    public Person(Long id, String name, ArrayList<Record> records) {
        this.id = id;
        this.name = name;
        this.records = records;
    }

    public Person() {

    }
}
