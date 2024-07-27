package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.DocumentDTO;
import tech.fiap.project.domain.entity.Document;

import java.util.List;

public class DocumentMapper {

	public static List<DocumentDTO> toDTO(List<Document> document) {
		return document.stream().map(DocumentMapper::toDTO).toList();
	}

	public static DocumentDTO toDTO(Document document) {
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setType(document.getType());
		documentDTO.setValue(document.getValue());
		return documentDTO;
	}

	public static List<Document> toDomain(List<DocumentDTO> documentDTO) {
		return documentDTO.stream().map(DocumentMapper::toDomain).toList();
	}

	public static Document toDomain(DocumentDTO documentDTO) {
		return new Document(documentDTO.getType(), documentDTO.getValue());
	}

}
