package tech.fiap.project.app.adapter;

import org.junit.jupiter.api.Test;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.app.dto.OrderRequestDTO;
import tech.fiap.project.app.dto.OrderResponseDTO;
import tech.fiap.project.domain.entity.*;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderMapperTest {

	@Test
	void toResponse_shouldMapOrderToOrderResponseDTO() {
		Order order = new Order(1L, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Duration.ZERO, new Person(), BigDecimal.TEN);

		OrderResponseDTO orderResponseDTO = OrderMapper.toDTO(order);

		assertNotNull(orderResponseDTO);
		assertEquals(order.getId(), orderResponseDTO.getId());
		assertEquals(order.getStatus(), orderResponseDTO.getStatus());
		assertEquals(order.getCreatedDate(), orderResponseDTO.getCreatedDate());
		assertEquals(order.getUpdatedDate(), orderResponseDTO.getUpdatedDate());
		assertEquals(order.getTotalPrice(), orderResponseDTO.getTotalPrice());
	}

	@Test
	void toDomain_shouldMapOrderResponseDTOToOrder() {
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
		orderResponseDTO.setId(1L);
		orderResponseDTO.setStatus(OrderStatus.PENDING);
		orderResponseDTO.setCreatedDate(LocalDateTime.now());
		orderResponseDTO.setUpdatedDate(LocalDateTime.now());
		orderResponseDTO.setTotalPrice(BigDecimal.TEN);

		Order order = OrderMapper.toDomain(orderResponseDTO);

		assertNotNull(order);
		assertEquals(orderResponseDTO.getId(), order.getId());
		assertEquals(orderResponseDTO.getStatus(), order.getStatus());
		assertEquals(orderResponseDTO.getCreatedDate(), order.getCreatedDate());
		assertEquals(orderResponseDTO.getUpdatedDate(), order.getUpdatedDate());
		assertEquals(orderResponseDTO.getTotalPrice(), order.getTotalPrice());
	}

	@Test
	void toDomain_shouldMapOrderRequestDTOToOrder() {
		OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
		orderRequestDTO.setId(1L);

		Order order = OrderMapper.toDomain(orderRequestDTO);

		assertNotNull(order);
		assertEquals(orderRequestDTO.getId(), order.getId());
	}

	@Test
	void toDTO_shouldMapOrderToOrderResponseDTO() {
		List<Item> items = List.of(new Item(1L, "item", BigDecimal.TEN, BigDecimal.ONE, "unit", ItemCategory.FOOD,
				Collections.emptyList(), "description", null));
		Order order = new Order(1L, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now(), items, Duration.ZERO,
				new Person(), BigDecimal.TEN);

		OrderResponseDTO orderResponseDTO = OrderMapper.toDTO(order);

		assertNotNull(orderResponseDTO);
		assertEquals(order.getId(), orderResponseDTO.getId());
		assertEquals(order.getStatus(), orderResponseDTO.getStatus());
		assertEquals(order.getCreatedDate(), orderResponseDTO.getCreatedDate());
		assertEquals(order.getUpdatedDate(), orderResponseDTO.getUpdatedDate());
		assertEquals(order.getTotalPrice(), orderResponseDTO.getTotalPrice());
	}

	@Test
	void toDTOList_shouldMapOrderListToOrderResponseDTOList() {
		Order order = new Order(1L, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Duration.ZERO, new Person(), BigDecimal.TEN);
		List<Order> orders = List.of(order, order);

		List<OrderResponseDTO> orderResponseDTOs = OrderMapper.toDTO(orders);

		assertNotNull(orderResponseDTOs);
		assertEquals(orders.size(), orderResponseDTOs.size());
		assertEquals(order.getId(), orderResponseDTOs.get(0).getId());
	}

	@Test
	void toDTO_shouldMapOrderToOrderResponseDTOWithKitchen() {
		List<Item> items = List.of(new Item(1L, "item", BigDecimal.TEN, BigDecimal.ONE, "unit", ItemCategory.FOOD,
				Collections.emptyList(), "description", null));
		Order order = new Order(1L, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now(), items, Duration.ZERO,
				new Person(), BigDecimal.TEN);
		Kitchen kitchen = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.IN_PRODUCTION);
		kitchen.setOrderId(1L);

		OrderResponseDTO orderResponseDTO = OrderMapper.toDTO(order, Optional.of(kitchen));

		assertNotNull(orderResponseDTO);
		assertEquals(order.getId(), orderResponseDTO.getId());
		assertEquals(order.getStatus(), orderResponseDTO.getStatus());
		assertEquals(order.getCreatedDate(), orderResponseDTO.getCreatedDate());
		assertEquals(order.getUpdatedDate(), orderResponseDTO.getUpdatedDate());
		assertEquals(order.getTotalPrice(), orderResponseDTO.getTotalPrice());
		assertNotNull(orderResponseDTO.getKitchenQueue());
	}

	@Test
	void toDTO_shouldMapOrderToOrderResponseDTOWithoutKitchen() {
		List<Item> items = List.of(new Item(1L, "item", BigDecimal.TEN, BigDecimal.ONE, "unit", ItemCategory.FOOD,
				Collections.emptyList(), "description", null));
		Order order = new Order(1L, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now(), items, Duration.ZERO,
				new Person(), BigDecimal.TEN);

		OrderResponseDTO orderResponseDTO = OrderMapper.toDTO(order, Optional.empty());

		assertNotNull(orderResponseDTO);
		assertEquals(order.getId(), orderResponseDTO.getId());
		assertEquals(order.getStatus(), orderResponseDTO.getStatus());
		assertEquals(order.getCreatedDate(), orderResponseDTO.getCreatedDate());
		assertEquals(order.getUpdatedDate(), orderResponseDTO.getUpdatedDate());
		assertEquals(order.getTotalPrice(), orderResponseDTO.getTotalPrice());
		assertEquals(1, orderResponseDTO.getItems().size());
		ItemDTO itemDTO = orderResponseDTO.getItems().get(0);
		assertEquals("item", itemDTO.getName());
		assertEquals(BigDecimal.TEN, itemDTO.getPrice());
		assertEquals(BigDecimal.ONE, itemDTO.getQuantity());
		assertEquals("unit", itemDTO.getUnit());
		assertEquals(ItemCategory.FOOD, itemDTO.getCategory());
		assertEquals("description", itemDTO.getDescription());
		assertNotNull(itemDTO.getIngredients());
		assertEquals(0, itemDTO.getIngredients().size());
	}

}