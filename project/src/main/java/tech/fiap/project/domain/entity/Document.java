package tech.fiap.project.domain.entity;

public class Document {

	private DocumentType type;

	private String value;

	public Document(DocumentType type, String value) {
		this.type = type;
		this.value = value;
	}

	public DocumentType getType() {
		return type;
	}

	public void setType(DocumentType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Document{" +
				"type=" + type +
				", value='" + value + '\'' +
				'}';
	}
}