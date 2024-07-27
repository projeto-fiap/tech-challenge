package tech.fiap.project.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import tech.fiap.project.infra.entity.PersonEntity;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long>, QuerydslPredicateExecutor<PersonEntity> {

	Optional<PersonEntity> findByEmail(String email);

	Optional<PersonEntity> findByEmailAndPassword(String email, String password);

}