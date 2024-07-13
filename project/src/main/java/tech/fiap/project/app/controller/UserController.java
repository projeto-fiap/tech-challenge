package tech.fiap.project.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.fiap.project.app.adapter.UserMapper;
import tech.fiap.project.domain.entity.User;
import tech.fiap.project.infra.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @GetMapping("/{email}")
    private User getUser(@PathVariable  String email) {
        return userMapper.toDTO(userRepository.findByEmail(email));
    }
    @GetMapping("/{id}")
    private User getUser(@PathVariable  Long id) {
        return userMapper.toDTO(userRepository.findById(id).orElse(null));
    }
    @GetMapping
    private List<User> getUsers() {
        return userMapper.toDTO(userRepository.findAll());
    }

    @PostMapping
    private User saveUser(User user) {
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(user)));
    }
    @PutMapping
    private User updateUser(User user) {
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(user)));
    }
    @DeleteMapping
    private void deleteUser(User user) {
        userRepository.delete(userMapper.toEntity(user));
    }
}
