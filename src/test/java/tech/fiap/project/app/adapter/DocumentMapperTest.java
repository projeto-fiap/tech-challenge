package tech.fiap.project.app.adapter;

import org.junit.jupiter.api.Test;
import tech.fiap.project.app.dto.DocumentDTO;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.infra.entity.DocumentEntity;
import tech.fiap.project.infra.entity.PersonEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DocumentMapperTest {

	@Test
	void toDTO_shouldMapDocumentListToDocumentDTOList() {
		List<Document> documents = List.of(new Document(DocumentType.CPF, "12345678900"));

		List<DocumentDTO> documentDTOs = DocumentMapper.toDTO(documents);

		assertNotNull(documentDTOs);
		assertEquals(documents.size(), documentDTOs.size());
		assertEquals(documents.get(0).getType(), documentDTOs.get(0).getType());
		assertEquals(documents.get(0).getValue(), documentDTOs.get(0).getValue());
	}

	@Test
	void toDTO_shouldReturnNullWhenDocumentListIsNull() {
		List<DocumentDTO> documentDTOs = DocumentMapper.toDTO(null);

		assertNull(documentDTOs);
	}

	@Test
	void toDomain_shouldMapDocumentDTOListToDocumentList() {
		List<DocumentDTO> documentDTOs = List.of(new DocumentDTO(DocumentType.CPF, "12345678900"));

		List<Document> documents = DocumentMapper.toDomain(documentDTOs);

		assertNotNull(documents);
		assertEquals(documentDTOs.size(), documents.size());
		assertEquals(documentDTOs.get(0).getType(), documents.get(0).getType());
		assertEquals(documentDTOs.get(0).getValue(), documents.get(0).getValue());
	}

	@Test
	void toDomain_shouldReturnNullWhenDocumentDTOListIsNull() {
		List<Document> documents = DocumentMapper.toDomain(null);

		assertNull(documents);
	}

	@Test
	void toEntity_shouldMapDocumentListToDocumentEntityList() {
		PersonEntity personEntity = new PersonEntity();
		List<Document> documents = List.of(new Document(DocumentType.CPF, "12345678900"));

		List<DocumentEntity> documentEntities = DocumentMapper.toEntity(documents, personEntity);

		assertNotNull(documentEntities);
		assertEquals(documents.size(), documentEntities.size());
		assertEquals(documents.get(0).getType(), documentEntities.get(0).getType());
		assertEquals(documents.get(0).getValue(), documentEntities.get(0).getValue());
		assertEquals(personEntity, documentEntities.get(0).getPerson());
	}

	@Test
	void toEntity_shouldReturnNullWhenDocumentListIsNull() {
		PersonEntity personEntity = new PersonEntity();
		List<DocumentEntity> documentEntities = DocumentMapper.toEntity(null, personEntity);

		assertNull(documentEntities);
	}

}