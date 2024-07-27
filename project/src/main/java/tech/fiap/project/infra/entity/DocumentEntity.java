package tech.fiap.project.infra.entity;

import jakarta.persistence.*;
import lombok.Data;
import tech.fiap.project.domain.entity.DocumentType;

@Entity
@Table(name = "document")
@Data
public class DocumentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String value;

	@Enumerated(EnumType.STRING)
	private DocumentType type;

	@ManyToOne
	private PersonEntity user;

}
