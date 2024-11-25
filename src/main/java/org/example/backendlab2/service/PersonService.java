package org.example.backendlab2.service;

import org.example.backendlab2.model.Person;
import org.example.backendlab2.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // Створення нового користувача з валідацією
    public Person createUser(String name) {
        validateName(name);
        Person person = new Person();
        person.setName(name);
        person.setRecords(new ArrayList<>()); // Ініціалізація пустого списку записів
        return personRepository.save(person);
    }

    // Пошук користувача за ID
    public Optional<Person> getUserById(Long id) {
        validateId(id);
        return personRepository.findById(id);
    }

    // Пошук користувача за ім'ям
    public Optional<Person> getUserByName(String name) {
        validateName(name);
        return Optional.ofNullable(personRepository.findByName(name));
    }

    // Видалення користувача за ID
    public void deleteUser(Long id) {
        validateId(id);
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " does not exist.");
        }
    }

    // Отримання списку всіх користувачів
    public List<Person> getAllUsers() {
        return new ArrayList<>((Collection) personRepository.findAll());
    }

    // Оновлення даних користувача з валідацією
    public Person updateUser(Long id, String newName) {
        validateId(id);
        validateName(newName);
        Optional<Person> personOpt = personRepository.findById(id);
        if (personOpt.isPresent()) {
            Person person = personOpt.get();
            person.setName(newName);
            return personRepository.save(person);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " does not exist.");
        }
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
