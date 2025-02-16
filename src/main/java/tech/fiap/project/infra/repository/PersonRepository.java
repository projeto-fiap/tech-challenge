package tech.fiap.project.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import tech.fiap.project.infra.entity.PersonEntity;
import tech.fiap.project.domain.entity.DocumentType;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

	Optional<PersonEntity> findByDocuments_TypeAndDocuments_Value(DocumentType type, String value);

}