package org.example.backendlab2.controller;

import org.example.backendlab2.model.Person;
import org.example.backendlab2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping("/user")
    public Person createUser(@RequestParam String name) {
        return personService.createUser(name);
    }

    @GetMapping("/user/{userId}")
    public Person getUser(@PathVariable Long userId) {
        return personService.getUser(userId).orElseThrow(() -> new RuntimeException("Person not found"));
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        personService.deleteUser(userId);
    }

    @GetMapping("/users")
    public List<Person> getAllUsers() {
        return personService.getAllUsers();
    }
}
