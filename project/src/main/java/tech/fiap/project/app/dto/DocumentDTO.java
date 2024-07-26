package tech.fiap.project.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.fiap.project.domain.entity.DocumentType;

@Data
@AllArgsConstructor
public class DocumentDTO {

	private DocumentType type;

	private String value;

}