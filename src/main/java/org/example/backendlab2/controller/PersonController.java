package org.example.backendlab2.controller;

import org.example.backendlab2.model.Person;
import org.example.backendlab2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class PersonController {

    @Autowired
    private PersonService personService;

    // Створення нового користувача
    @PostMapping("/user")
    public ResponseEntity<Person> createUser(@RequestParam String name) {
        Person createdPerson = personService.createUser(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

    // Отримання користувача за ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<Person> getUser(@PathVariable Long userId) {
        Optional<Person> person = personService.getUserById(userId);
        if (person.isPresent()) {
            return ResponseEntity.ok(person.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Видалення користувача за ID
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        try {
            personService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Отримання списку всіх користувачів
    @GetMapping("/users")
    public ResponseEntity<List<Person>> getAllUsers() {
        List<Person> users = personService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
