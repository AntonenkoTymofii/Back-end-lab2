package org.example.backendlab2.repository;

import org.example.backendlab2.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
