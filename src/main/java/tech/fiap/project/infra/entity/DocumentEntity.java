package tech.fiap.project.infra.entity;

import jakarta.persistence.*;
import lombok.*;
import tech.fiap.project.domain.entity.DocumentType;

@Entity
@Table(name = "document")
@Getter
@Setter
@RequiredArgsConstructor
public class DocumentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String value;

	@Enumerated(EnumType.STRING)
	private DocumentType type;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false)
	@ToString.Exclude // Exclui do toString para evitar ciclos
	private PersonEntity person;

}
