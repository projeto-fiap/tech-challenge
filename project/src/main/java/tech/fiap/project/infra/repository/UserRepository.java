package tech.fiap.project.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import tech.fiap.project.infra.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>, QuerydslPredicateExecutor<UserEntity>{

	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByEmailAndPassword(String email, String password);

}