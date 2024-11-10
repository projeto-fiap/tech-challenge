
package tech.fiap.project.infra.mapper;

import org.junit.jupiter.api.Test;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.infra.entity.DocumentEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DocumentRepositoryMapperTest {

	@Test
	void toEntity_shouldMapDocumentToDocumentEntity() {
		Document document = new Document(DocumentType.CPF, "value");
		DocumentEntity documentEntity = DocumentRepositoryMapper.toEntity(document);

		assertEquals(document.getType(), documentEntity.getType());
		assertEquals(document.getValue(), documentEntity.getValue());
	}

	@Test
	void toEntity_shouldReturnNullWhenDocumentIsNull() {
		Document document = null;
		DocumentEntity documentEntity = DocumentRepositoryMapper.toEntity(document);
		assertNull(documentEntity);
	}

	@Test
	void toEntity_shouldMapListOfDocumentsToListOfDocumentEntities() {
		List<Document> documents = List.of(new Document(DocumentType.CPF, "value1"),
				new Document(DocumentType.CPF, "value2"));
		List<DocumentEntity> documentEntities = DocumentRepositoryMapper.toEntity(documents);

		assertEquals(documents.size(), documentEntities.size());
		for (int i = 0; i < documents.size(); i++) {
			assertEquals(documents.get(i).getType(), documentEntities.get(i).getType());
			assertEquals(documents.get(i).getValue(), documentEntities.get(i).getValue());
		}
	}

	@Test
	void toDomain_shouldMapDocumentEntityToDocument() {
		DocumentEntity documentEntity = new DocumentEntity();
		documentEntity.setType(DocumentType.CPF);
		documentEntity.setValue("value");
		Document document = DocumentRepositoryMapper.toDomain(documentEntity);

		assertEquals(documentEntity.getType(), document.getType());
		assertEquals(documentEntity.getValue(), document.getValue());
	}

	@Test
	void toDomain_shouldReturnNullWhenDocumentEntityIsNull() {
		Document document = DocumentRepositoryMapper.toDomain((DocumentEntity) null);
		assertNull(document);
	}

	@Test
	void toDomain_shouldMapListOfDocumentEntitiesToListOfDocuments() {
		List<DocumentEntity> documentEntities = List.of(new DocumentEntity(), new DocumentEntity());
		List<Document> documents = DocumentRepositoryMapper.toDomain(documentEntities);

		assertEquals(documentEntities.size(), documents.size());
		for (int i = 0; i < documentEntities.size(); i++) {
			assertEquals(documentEntities.get(i).getType(), documents.get(i).getType());
			assertEquals(documentEntities.get(i).getValue(), documents.get(i).getValue());
		}
	}

}
