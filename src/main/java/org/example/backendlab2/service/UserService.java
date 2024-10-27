package org.example.backendlab2.service;

import org.example.backendlab2.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private Map<Long, User> users = new HashMap<>();
    private AtomicLong userIdCounter = new AtomicLong();

    public User createUser(String name) {
        Long id = userIdCounter.incrementAndGet();
        User user = new User(id, name);
        users.put(id, user);
        return user;
    }

    public Optional<User> getUser(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public void deleteUser(Long id) {
        users.remove(id);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

}
