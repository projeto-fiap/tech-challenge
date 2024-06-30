package tech.fiap.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.fiap.project.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}