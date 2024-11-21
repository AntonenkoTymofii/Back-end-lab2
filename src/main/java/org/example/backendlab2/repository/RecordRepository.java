package org.example.backendlab2.repository;

import org.example.backendlab2.model.Record;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecordRepository extends CrudRepository<Record, Long> {
    List<Record> findByPerson_Id(Long id);
    List<Record> findByPerson_Name(String name);
    List<Record> findByCategory_Id(Long id);
    List<Record> findByCategory_Name(String name);
}
