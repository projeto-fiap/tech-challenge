package tech.fiap.project.app.dto;

import lombok.Data;
import tech.fiap.project.domain.entity.DocumentType;

@Data
public class DocumentDTO {

	private DocumentType type;

	private String value;

	public DocumentDTO(DocumentType type, String value) {
		this.type = type;
		this.value = value;
	}

	public DocumentDTO() {

	}

}