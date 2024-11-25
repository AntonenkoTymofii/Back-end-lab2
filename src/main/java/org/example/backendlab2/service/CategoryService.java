package org.example.backendlab2.service;

import org.example.backendlab2.model.Category;
import org.example.backendlab2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Створення нової категорії
    public Category createCategory(String name) {
        validateName(name);
        if (categoryRepository.findByName(name) != null) {
            throw new IllegalArgumentException("Category with name '" + name + "' already exists.");
        }
        Category category = new Category();
        category.setName(name);
        category.setRecords(new ArrayList<>());
        return categoryRepository.save(category);
    }

    // Отримання категорії за ID
    public Optional<Category> getCategoryById(Long id) {
        validateId(id);
        return categoryRepository.findById(id);
    }

    // Отримання категорії за ім'ям
    public Optional<Category> getCategoryByName(String name) {
        validateName(name);
        return Optional.ofNullable(categoryRepository.findByName(name));
    }

    // Оновлення категорії
    public Category updateCategory(Long id, String newName) {
        validateId(id);
        validateName(newName);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category with ID " + id + " does not exist."));

        if (categoryRepository.findByName(newName) != null) {
            throw new IllegalArgumentException("Category with name '" + newName + "' already exists.");
        }

        category.setName(newName);
        return categoryRepository.save(category);
    }

    // Видалення категорії
    public void deleteCategory(Long id) {
        validateId(id);
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Category with ID " + id + " does not exist.");
        }
    }

    // Отримання всіх категорій
    public List<Category> getAllCategories() {
        return new ArrayList<>((Collection) categoryRepository.findAll());
    }

    // Валідація ID
    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number.");
        }
    }

    // Валідація імені
    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty.");
        }
        if (name.length() > 255) {
            throw new IllegalArgumentException("Name length must not exceed 255 characters.");
        }
    }
}
