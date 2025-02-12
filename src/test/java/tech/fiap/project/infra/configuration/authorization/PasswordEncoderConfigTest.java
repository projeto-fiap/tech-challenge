package tech.fiap.project.infra.configuration.authorization;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderConfigTest {

    @Test
    void testPasswordEncoderBean() {
        PasswordEncoderConfig config = new PasswordEncoderConfig();
        PasswordEncoder passwordEncoder = config.passwordEncoder();

        assertNotNull(passwordEncoder);
        assertTrue(passwordEncoder instanceof BCryptPasswordEncoder);

        String rawPassword = "password";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
    }
}