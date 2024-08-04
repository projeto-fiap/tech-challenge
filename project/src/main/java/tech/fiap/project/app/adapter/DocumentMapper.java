// DocumentMapper.java
package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.DocumentDTO;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.infra.entity.DocumentEntity;
import tech.fiap.project.infra.entity.PersonEntity;

import java.util.List;
import java.util.stream.Collectors;

public class DocumentMapper {

	public static List<DocumentDTO> toDTO(List<Document> documents) {
		if (documents == null) {
			return null;
		}
		return documents.stream().map(document -> new DocumentDTO(document.getType(), document.getValue()))
				.collect(Collectors.toList());
	}

	public static List<Document> toDomain(List<DocumentDTO> documentDTOs) {
		if (documentDTOs == null) {
			return null;
		}
		return documentDTOs.stream().map(documentDTO -> new Document(documentDTO.getType(), documentDTO.getValue()))
				.collect(Collectors.toList());
	}

	public static List<DocumentEntity> toEntity(List<Document> documents, PersonEntity personEntity) {
		if (documents == null) {
			return null;
		}
		return documents.stream().map(document -> {
			DocumentEntity documentEntity = new DocumentEntity();
			documentEntity.setType(document.getType());
			documentEntity.setValue(document.getValue());
			documentEntity.setPerson(personEntity);
			return documentEntity;
		}).collect(Collectors.toList());
	}

}