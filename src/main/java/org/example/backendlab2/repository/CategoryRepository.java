package org.example.backendlab2.repository;

import org.example.backendlab2.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
