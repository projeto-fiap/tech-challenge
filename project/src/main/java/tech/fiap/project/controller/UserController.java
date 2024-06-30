package tech.fiap.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.fiap.project.model.User;
import tech.fiap.project.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{email}")
    private User getUser(@PathVariable  String email) {
        return userRepository.findByEmail(email);
    }
    @GetMapping("/{id}")
    private User getUser(@PathVariable  Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @GetMapping
    private List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    private User saveUser(User user) {
        return userRepository.save(user);
    }
    @PutMapping
    private User updateUser(User user) {
        return userRepository.save(user);
    }
    @DeleteMapping
    private void deleteUser(User user) {
        userRepository.delete(user);
    }
}
