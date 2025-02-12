package tech.fiap.project.app.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.fiap.project.domain.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderResponsePaymentDTOTest {

    private OrderResponsePaymentDTO orderResponsePaymentDTO;

    @BeforeEach
    void setUp() {
        List<ItemDTO> items = Collections.singletonList(new ItemDTO());
        PersonDTO person = new PersonDTO();
        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime updatedDate = LocalDateTime.now();
        Duration awaitingTime = Duration.ofMinutes(30);
        BigDecimal totalPrice = new BigDecimal("100.00");

        orderResponsePaymentDTO = new OrderResponsePaymentDTO(
                1L,
                OrderStatus.PENDING,
                createdDate,
                updatedDate,
                items,
                person,
                awaitingTime,
                totalPrice
        );
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1L, orderResponsePaymentDTO.getId());
        assertEquals(OrderStatus.PENDING, orderResponsePaymentDTO.getStatus());
        assertNotNull(orderResponsePaymentDTO.getCreatedDate());
        assertNotNull(orderResponsePaymentDTO.getUpdatedDate());
        assertEquals(1, orderResponsePaymentDTO.getItems().size());
        assertNotNull(orderResponsePaymentDTO.getPerson());
        assertEquals(Duration.ofMinutes(30), orderResponsePaymentDTO.getAwaitingTime());
        assertEquals(new BigDecimal("100.00"), orderResponsePaymentDTO.getTotalPrice());

        // Test setters
        orderResponsePaymentDTO.setId(2L);
        orderResponsePaymentDTO.setStatus(OrderStatus.FINISHED);
        orderResponsePaymentDTO.setCreatedDate(LocalDateTime.now().plusDays(1));
        orderResponsePaymentDTO.setUpdatedDate(LocalDateTime.now().plusDays(1));
        orderResponsePaymentDTO.setItems(Collections.emptyList());
        orderResponsePaymentDTO.setPerson(new PersonDTO());
        orderResponsePaymentDTO.setAwaitingTime(Duration.ofHours(1));
        orderResponsePaymentDTO.setTotalPrice(new BigDecimal("200.00"));

        assertEquals(2L, orderResponsePaymentDTO.getId());
        assertEquals(OrderStatus.FINISHED, orderResponsePaymentDTO.getStatus());
        assertNotNull(orderResponsePaymentDTO.getCreatedDate());
        assertNotNull(orderResponsePaymentDTO.getUpdatedDate());
        assertEquals(0, orderResponsePaymentDTO.getItems().size());
        assertNotNull(orderResponsePaymentDTO.getPerson());
        assertEquals(Duration.ofHours(1), orderResponsePaymentDTO.getAwaitingTime());
        assertEquals(new BigDecimal("200.00"), orderResponsePaymentDTO.getTotalPrice());
    }

    @Test
    void testAllArgsConstructor() {
        List<ItemDTO> items = Collections.singletonList(new ItemDTO());
        PersonDTO person = new PersonDTO();
        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime updatedDate = LocalDateTime.now();
        Duration awaitingTime = Duration.ofMinutes(30);
        BigDecimal totalPrice = new BigDecimal("100.00");

        OrderResponsePaymentDTO dto = new OrderResponsePaymentDTO(
                1L,
                OrderStatus.PENDING,
                createdDate,
                updatedDate,
                items,
                person,
                awaitingTime,
                totalPrice
        );

        assertEquals(1L, dto.getId());
        assertEquals(OrderStatus.PENDING, dto.getStatus());
        assertEquals(createdDate, dto.getCreatedDate());
        assertEquals(updatedDate, dto.getUpdatedDate());
        assertEquals(items, dto.getItems());
        assertEquals(person, dto.getPerson());
        assertEquals(awaitingTime, dto.getAwaitingTime());
        assertEquals(totalPrice, dto.getTotalPrice());
    }
}