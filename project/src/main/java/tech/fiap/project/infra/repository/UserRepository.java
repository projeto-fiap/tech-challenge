package tech.fiap.project.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.fiap.project.domain.entity.User;
import tech.fiap.project.infra.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findByEmailAndPassword(String email, String password);
}