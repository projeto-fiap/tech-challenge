package tech.fiap.project.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

	private Document document;

	@BeforeEach
	void setUp() {
		document = new Document();
	}

	@Test
	void testConstructorWithParameters() {
		DocumentType type = DocumentType.CPF;
		String value = "12345678901";

		Document documentWithParams = new Document(type, value);

		assertEquals(type, documentWithParams.getType());
		assertEquals(value, documentWithParams.getValue());
	}

	@Test
	void testDefaultConstructor() {
		assertNotNull(document);
		assertNull(document.getType());
		assertNull(document.getValue());
	}

	@Test
	void testSetAndGetType() {
		DocumentType type = DocumentType.CPF;
		document.setType(type);

		assertEquals(type, document.getType());
	}

	@Test
	void testSetAndGetValue() {
		String value = "12345678901";
		document.setValue(value);

		assertEquals(value, document.getValue());
	}

	@Test
	void testToString() {
		DocumentType type = DocumentType.CPF;
		String value = "12345678901";
		document.setType(type);
		document.setValue(value);

		String expectedToString = "Document{type=" + type + ", value='" + value + "'}";
		assertEquals(expectedToString, document.toString());
	}

}