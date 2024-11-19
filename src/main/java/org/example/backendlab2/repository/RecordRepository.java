package org.example.backendlab2.repository;

import org.example.backendlab2.model.Record;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<Record, Long> {
}
