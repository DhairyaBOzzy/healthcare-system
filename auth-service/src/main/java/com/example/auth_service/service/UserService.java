package com.example.authservice.service;

import com.example.authservice.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<String, User> userDatabase = new HashMap<>();

    public UserService() {
        // Hardcoded users for demonstration
        userDatabase.put("user", new User("user", "pass"));
        userDatabase.put("admin", new User("admin", "adminpass"));
    }

    public User findByUsername(String username) {
        return userDatabase.get(username);
    }
}
