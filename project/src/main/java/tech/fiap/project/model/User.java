package tech.fiap.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    private String token;

    private String refreshToken;

    private Role role;

}