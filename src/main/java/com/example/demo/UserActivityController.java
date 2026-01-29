package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserActivityController {

    private static final List<User> users = new ArrayList<>();

    @PostMapping
    public User addUser(@RequestBody User user) {
        users.add(user);
        return user;
    }

    @GetMapping
    public List<User> getUsers() {
        return users;
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PutMapping("/{username}")
    public Post addPost(@PathVariable String username, @RequestBody Post post) {
        users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .ifPresent(u -> u.getPosts().add(post));
        return post;
    }

    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable String username) {
        boolean removed = users.removeIf(u -> u.getUsername().equals(username));
        return removed
                ? "User " + username + " deleted"
                : "User " + username + " not found";
    }
}