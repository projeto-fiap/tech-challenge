package tech.fiap.project.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.infra.entity.DocumentEntity;

import java.util.Optional;

public interface DocumentRepository
		extends JpaRepository<DocumentEntity, Long>, QuerydslPredicateExecutor<DocumentEntity> {

	Optional<DocumentEntity> findByTypeAndValue(DocumentType type, String value);

}