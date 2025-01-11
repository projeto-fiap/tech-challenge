package tech.fiap.project.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.fiap.project.infra.configuration.authorization.CustomPersonDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomPersonDetailsService customPersonDetailsService;

    @Autowired
    public AuthController(CustomPersonDetailsService customPersonDetailsService) {
        this.customPersonDetailsService = customPersonDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String cpf, @RequestParam String password) {
        try {
            String token = customPersonDetailsService.authenticateAndGenerateToken(cpf, password);
            return ResponseEntity.ok(token);
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request: " + ex.getMessage());
        }
    }
}
