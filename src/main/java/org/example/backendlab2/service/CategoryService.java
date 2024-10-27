package org.example.backendlab2.service;

import org.example.backendlab2.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CategoryService {
    private Map<Long, Category> categories = new HashMap<>();
    private AtomicLong categoryIdCounter = new AtomicLong();

    public Category createCategory(String name) {
        Long id = categoryIdCounter.incrementAndGet();
        Category category = new Category(id, name);
        categories.put(id, category);
        return category;
    }

    public void deleteCategory(Long id) {
        categories.remove(id);
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }
}
