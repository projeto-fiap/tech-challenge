// DocumentMapper.java
package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.DocumentDTO;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.infra.entity.DocumentEntity;
import tech.fiap.project.infra.entity.PersonEntity;

import java.util.ArrayList;
import java.util.List;

public class DocumentMapper {

	private DocumentMapper() {
	}

	public static List<DocumentDTO> toDTO(List<Document> documents) {
		if (documents == null) {
			return new ArrayList<>();
		}
		return documents.stream().map(document -> new DocumentDTO(document.getType(), document.getValue())).toList();
	}

	public static List<Document> toDomain(List<DocumentDTO> documentDTOs) {
		if (documentDTOs == null) {
			return new ArrayList<>();
		}
		return documentDTOs.stream().map(documentDTO -> new Document(documentDTO.getType(), documentDTO.getValue()))
				.toList();
	}

	public static List<DocumentEntity> toEntity(List<Document> documents, PersonEntity personEntity) {
		if (documents == null) {
			return new ArrayList<>();
		}
		return documents.stream().map(document -> {
			DocumentEntity documentEntity = new DocumentEntity();
			documentEntity.setType(document.getType());
			documentEntity.setValue(document.getValue());
			documentEntity.setPerson(personEntity);
			return documentEntity;
		}).toList();
	}

}