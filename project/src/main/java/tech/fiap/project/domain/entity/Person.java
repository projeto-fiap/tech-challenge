package tech.fiap.project.domain.entity;

import java.util.List;

public class Person {

	private Long id;

	private String email;

	private String password;

	private Role role;

	private List<Document> document;

	public Person(Long id, String email, String password, List<Document> document, Role role) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.document = document;
		this.role = role;
	}

	public List<Document> getDocument() {
		return document;
	}

	public void setDocument(List<Document> document) {
		this.document = document;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", email='" + email + '\'' + ", password='" + password + '\'' + ", document="
				+ document + '}';
	}

}