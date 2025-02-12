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

class OrderResponseDTOTest {

    private OrderResponseDTO orderResponseDTO;

    @BeforeEach
    void setUp() {
        List<ItemDTO> items = Collections.singletonList(new ItemDTO());
        PersonDTO person = new PersonDTO();
        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime updatedDate = LocalDateTime.now();
        Duration awaitingTime = Duration.ofMinutes(30);
        BigDecimal totalPrice = new BigDecimal("100.00");
        KitchenDTO kitchenQueue = new KitchenDTO();

        orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setId(1L);
        orderResponseDTO.setStatus(OrderStatus.PENDING);
        orderResponseDTO.setCreatedDate(createdDate);
        orderResponseDTO.setUpdatedDate(updatedDate);
        orderResponseDTO.setItems(items);
        orderResponseDTO.setPerson(person);
        orderResponseDTO.setAwaitingTime(awaitingTime);
        orderResponseDTO.setTotalPrice(totalPrice);
        orderResponseDTO.setKitchenQueue(kitchenQueue);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1L, orderResponseDTO.getId());
        assertEquals(OrderStatus.PENDING, orderResponseDTO.getStatus());
        assertNotNull(orderResponseDTO.getCreatedDate());
        assertNotNull(orderResponseDTO.getUpdatedDate());
        assertEquals(1, orderResponseDTO.getItems().size());
        assertNotNull(orderResponseDTO.getPerson());
        assertEquals(Duration.ofMinutes(30), orderResponseDTO.getAwaitingTime());
        assertEquals(new BigDecimal("100.00"), orderResponseDTO.getTotalPrice());
        assertNotNull(orderResponseDTO.getKitchenQueue());

        // Test setters
        orderResponseDTO.setId(2L);
        orderResponseDTO.setStatus(OrderStatus.FINISHED);
        orderResponseDTO.setCreatedDate(LocalDateTime.now().plusDays(1));
        orderResponseDTO.setUpdatedDate(LocalDateTime.now().plusDays(1));
        orderResponseDTO.setItems(Collections.emptyList());
        orderResponseDTO.setPerson(new PersonDTO());
        orderResponseDTO.setAwaitingTime(Duration.ofHours(1));
        orderResponseDTO.setTotalPrice(new BigDecimal("200.00"));
        orderResponseDTO.setKitchenQueue(new KitchenDTO());

        assertEquals(2L, orderResponseDTO.getId());
        assertEquals(OrderStatus.FINISHED, orderResponseDTO.getStatus());
        assertNotNull(orderResponseDTO.getCreatedDate());
        assertNotNull(orderResponseDTO.getUpdatedDate());
        assertEquals(0, orderResponseDTO.getItems().size());
        assertNotNull(orderResponseDTO.getPerson());
        assertEquals(Duration.ofHours(1), orderResponseDTO.getAwaitingTime());
        assertEquals(new BigDecimal("200.00"), orderResponseDTO.getTotalPrice());
        assertNotNull(orderResponseDTO.getKitchenQueue());
    }
}