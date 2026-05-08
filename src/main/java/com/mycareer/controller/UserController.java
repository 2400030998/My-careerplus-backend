package com.mycareer.controller;

import com.mycareer.model.User;
import com.mycareer.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // CREATE USER
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // GET ALL USERS
    @GetMapping(produces = "application/json")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}