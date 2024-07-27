package tech.fiap.project.infra.mapper;

import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.infra.entity.DocumentEntity;

import java.util.List;

public class DocumentRepositoryMapper {

	public static List<DocumentEntity> toEntity(List<Document> documentEntity) {
		return documentEntity.stream().map(DocumentRepositoryMapper::toEntity).toList();
	}

	public static DocumentEntity toEntity(Document document) {
		if (document == null) {
			return null;
		}
		else {
			DocumentEntity documentEntity = new DocumentEntity();
			documentEntity.setType(document.getType());
			documentEntity.setValue(document.getValue());
			return documentEntity;
		}
	}

	public static List<Document> toDomain(List<DocumentEntity> documentEntity) {
		return documentEntity.stream().map(DocumentRepositoryMapper::toDomain).toList();
	}

	public static Document toDomain(DocumentEntity documentEntity) {
		if (documentEntity == null) {
			return null;
		}
		else {
			return new Document(documentEntity.getType(), documentEntity.getValue());
		}
	}

}
