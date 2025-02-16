package tech.fiap.project.domain.entity;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testConstructor_AllArgs() {
        List<Document> documents = Collections.singletonList(new Document());
        Person person = new Person(1L, "John Doe", "john@example.com", "password123", Role.ADMIN, documents);

        assertEquals(1L, person.getId());
        assertEquals("John Doe", person.getName());
        assertEquals("john@example.com", person.getEmail());
        assertEquals("password123", person.getPassword());
        assertEquals(Role.ADMIN, person.getRole());
        assertEquals(documents, person.getDocument());
    }

    @Test
    void testConstructor_WithoutName() {
        List<Document> documents = Collections.singletonList(new Document());
        Person person = new Person(1L, "john@example.com", "password123", documents, Role.USER);

        assertEquals(1L, person.getId());
        assertNull(person.getName());
        assertEquals("john@example.com", person.getEmail());
        assertEquals("password123", person.getPassword());
        assertEquals(Role.USER, person.getRole());
        assertEquals(documents, person.getDocument());
    }

    @Test
    void testConstructor_WithoutId() {
        List<Document> documents = Collections.singletonList(new Document());
        Person person = new Person("john@example.com", "password123", Role.USER, documents);

        assertNull(person.getId());
        assertNull(person.getName());
        assertEquals("john@example.com", person.getEmail());
        assertEquals("password123", person.getPassword());
        assertEquals(Role.USER, person.getRole());
        assertEquals(documents, person.getDocument());
    }

    @Test
    void testConstructor_WithoutIdAndOnlyName() {
        List<Document> documents = Collections.singletonList(new Document());
        Person person = new Person("John Doe", "john@example.com", "password123", Role.ADMIN, documents);

        assertNull(person.getId());
        assertEquals("John Doe", person.getName());
        assertEquals("john@example.com", person.getEmail());
        assertEquals("password123", person.getPassword());
        assertEquals(Role.ADMIN, person.getRole());
        assertEquals(documents, person.getDocument());
    }

    @Test
    void testNoArgsConstructor() {
        Person person = new Person();
        assertNull(person.getId());
        assertNull(person.getName());
        assertNull(person.getEmail());
        assertNull(person.getPassword());
        assertNull(person.getRole());
        assertNull(person.getDocument());
    }
    @Test
    void toString_ShouldReturnProperFormat() {
        List<Document> documents = List.of(new Document());
        Person person = new Person(1L, "john@example.com", "password", documents, Role.USER);

        String expected = "Person{" + "id=1, email='john@example.com', password='password', document=" + documents + '}';
        assertEquals(expected, person.toString());
    }
}
