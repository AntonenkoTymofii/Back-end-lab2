package org.example.backendlab2.service;

import org.example.backendlab2.model.Person;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {
    private Map<Long, Person> users = new HashMap<>();
    private AtomicLong userIdCounter = new AtomicLong();

    public Person createUser(String name) {
        Long id = userIdCounter.incrementAndGet();
        Person person = new Person(id, name);
        users.put(id, person);
        return person;
    }

    public Optional<Person> getUser(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public void deleteUser(Long id) {
        users.remove(id);
    }

    public List<Person> getAllUsers() {
        return new ArrayList<>(users.values());
    }

}
