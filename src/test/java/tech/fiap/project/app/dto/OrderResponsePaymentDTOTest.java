package tech.fiap.project.app.dto;

import org.junit.jupiter.api.Test;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.entity.Role;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderResponsePaymentDTOTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        OrderStatus status = OrderStatus.PENDING;
        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime updatedDate = LocalDateTime.now();

        ItemDTO item = new ItemDTO();
        item.setId(1L);
        item.setName("Item 1");
        item.setPrice(BigDecimal.TEN);
        item.setQuantity(BigDecimal.ONE);
        List<ItemDTO> items = Collections.singletonList(item);

        PersonDTO person = new PersonDTO();
        person.setId(1L);
        person.setEmail("john.doe@example.com");
        person.setName("John Doe");
        person.setPassword("password");
        person.setRole(Role.USER);

        Duration awaitingTime = Duration.ofMinutes(30);
        BigDecimal totalPrice = BigDecimal.valueOf(100);

        // Act
        OrderResponsePaymentDTO dto = new OrderResponsePaymentDTO(id, status, createdDate, updatedDate, items, person, awaitingTime, totalPrice);

        // Assert
        assertEquals(id, dto.getId());
        assertEquals(status, dto.getStatus());
        assertEquals(createdDate, dto.getCreatedDate());
        assertEquals(updatedDate, dto.getUpdatedDate());
        assertEquals(items, dto.getItems());
        assertEquals(person, dto.getPerson());
        assertEquals(awaitingTime, dto.getAwaitingTime());
        assertEquals(totalPrice, dto.getTotalPrice());
    }

    @Test
    void testSetters() {
        // Arrange
        OrderResponsePaymentDTO dto = new OrderResponsePaymentDTO(null, null, null, null, null, null, null, null);

        Long id = 1L;
        OrderStatus status = OrderStatus.AWAITING_PAYMENT;
        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime updatedDate = LocalDateTime.now();

        ItemDTO item = new ItemDTO();
        item.setId(1L);
        item.setName("Item A");
        item.setPrice(BigDecimal.valueOf(50));
        item.setQuantity(BigDecimal.ONE);
        List<ItemDTO> items = Collections.singletonList(item);

        PersonDTO person = new PersonDTO();
        person.setId(2L);
        person.setEmail("jane.doe@example.com");
        person.setName("Jane Doe");
        person.setPassword("password123");
        person.setRole(Role.ADMIN);

        Duration awaitingTime = Duration.ofHours(1);
        BigDecimal totalPrice = BigDecimal.valueOf(200);

        // Act
        dto.setId(id);
        dto.setStatus(status);
        dto.setCreatedDate(createdDate);
        dto.setUpdatedDate(updatedDate);
        dto.setItems(items);
        dto.setPerson(person);
        dto.setAwaitingTime(awaitingTime);
        dto.setTotalPrice(totalPrice);

        // Assert
        assertEquals(id, dto.getId());
        assertEquals(status, dto.getStatus());
        assertEquals(createdDate, dto.getCreatedDate());
        assertEquals(updatedDate, dto.getUpdatedDate());
        assertEquals(items, dto.getItems());
        assertEquals(person, dto.getPerson());
        assertEquals(awaitingTime, dto.getAwaitingTime());
        assertEquals(totalPrice, dto.getTotalPrice());
    }


}
