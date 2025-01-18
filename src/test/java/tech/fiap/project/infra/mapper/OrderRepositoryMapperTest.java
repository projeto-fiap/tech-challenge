package tech.fiap.project.infra.mapper;

import org.junit.jupiter.api.Test;
import tech.fiap.project.domain.entity.*;
import tech.fiap.project.infra.entity.OrderEntity;
import tech.fiap.project.infra.entity.PersonEntity;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OrderRepositoryMapperTest {

	@Test
	void toEntity_shouldMapOrderToOrderEntity() {
		Person person = new Person("john.doe@example.com", "John Doe", Role.USER,
				List.of(new Document(DocumentType.CPF, "123456789")));
		Order order = new Order(1L, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now(), List.of(),
				Duration.of(10, ChronoUnit.SECONDS), person, BigDecimal.valueOf(100.0));
		OrderEntity orderEntity = OrderRepositoryMapper.toEntity(order);

		assertEquals(order.getId(), orderEntity.getId());
		assertEquals(order.getStatus().name(), orderEntity.getStatus());
		assertEquals(order.getCreatedDate(), orderEntity.getCreatedDate());
		assertEquals(order.getUpdatedDate(), orderEntity.getUpdatedDate());
		assertEquals(order.getPerson().getId(), orderEntity.getPerson().getId());
		assertEquals(order.getAwaitingTime(), orderEntity.getAwaitingTime());
		assertEquals(order.getTotalPrice(), orderEntity.getTotalPrice());
	}

	@Test
	void toEntity_shouldReturnNullWhenOrderIsNull() {
		OrderEntity orderEntity = OrderRepositoryMapper.toEntity((Order) null);
		assertNull(orderEntity);
	}

	@Test
	void toDomain_shouldMapOrderEntityToOrder() {
		PersonEntity personEntity = new PersonEntity();
		personEntity.setId(1L);
		personEntity.setName("John Doe");
		personEntity.setEmail("john.doe@example.com");

		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(1L);
		orderEntity.setStatus("PENDING");
		orderEntity.setCreatedDate(LocalDateTime.now());
		orderEntity.setUpdatedDate(LocalDateTime.now());
		orderEntity.setPerson(personEntity);
		orderEntity.setAwaitingTime(Duration.ofDays(10));
		orderEntity.setTotalPrice(BigDecimal.valueOf(100.0));

		Order order = OrderRepositoryMapper.toDomain(orderEntity);

		assertEquals(orderEntity.getId(), order.getId());
		assertEquals(orderEntity.getStatus(), order.getStatus().name());
		assertEquals(orderEntity.getCreatedDate(), order.getCreatedDate());
		assertEquals(orderEntity.getUpdatedDate(), order.getUpdatedDate());
		assertEquals(orderEntity.getPerson().getId(), order.getPerson().getId());
		assertEquals(orderEntity.getAwaitingTime(), order.getAwaitingTime());
		assertEquals(orderEntity.getTotalPrice(), order.getTotalPrice());
	}

	@Test
	void toDomain_shouldReturnNullWhenOrderEntityIsNull() {
		Order order = OrderRepositoryMapper.toDomain((OrderEntity) null);
		assertNull(order);
	}

	@Test
	void toDomainList_shouldMapOrderEntityListToOrderList() {
		PersonEntity personEntity = new PersonEntity();
		personEntity.setId(1L);
		personEntity.setName("John Doe");
		personEntity.setEmail("john.doe@example.com");

		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(1L);
		orderEntity.setStatus("PENDING");
		orderEntity.setCreatedDate(LocalDateTime.now());
		orderEntity.setUpdatedDate(LocalDateTime.now());
		orderEntity.setPerson(personEntity);
		orderEntity.setAwaitingTime(Duration.ofDays(10));
		orderEntity.setTotalPrice(BigDecimal.valueOf(100.0));

		List<OrderEntity> orderEntities = List.of(orderEntity);
		List<Order> orders = OrderRepositoryMapper.toDomain(orderEntities);

		assertEquals(1, orders.size());
		assertEquals(orderEntity.getId(), orders.get(0).getId());
		assertEquals(orderEntity.getStatus(), orders.get(0).getStatus().name());
		assertEquals(orderEntity.getCreatedDate(), orders.get(0).getCreatedDate());
		assertEquals(orderEntity.getUpdatedDate(), orders.get(0).getUpdatedDate());
		assertEquals(orderEntity.getPerson().getId(), orders.get(0).getPerson().getId());
		assertEquals(orderEntity.getAwaitingTime(), orders.get(0).getAwaitingTime());
		assertEquals(orderEntity.getTotalPrice(), orders.get(0).getTotalPrice());
	}

	@Test
	void toEntityList_shouldMapOrderListToOrderEntityList() {
		Person person = new Person("john.doe@example.com", "John Doe", Role.USER,
				List.of(new Document(DocumentType.CPF, "123456789")));
		Order order = new Order(1L, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now(), List.of(),
				Duration.of(10, ChronoUnit.SECONDS), person, BigDecimal.valueOf(100.0));

		List<Order> orders = List.of(order);
		List<OrderEntity> orderEntities = OrderRepositoryMapper.toEntity(orders);

		assertEquals(1, orderEntities.size());
		assertEquals(order.getId(), orderEntities.get(0).getId());
		assertEquals(order.getStatus().name(), orderEntities.get(0).getStatus());
		assertEquals(order.getCreatedDate(), orderEntities.get(0).getCreatedDate());
		assertEquals(order.getUpdatedDate(), orderEntities.get(0).getUpdatedDate());
		assertEquals(order.getPerson().getId(), orderEntities.get(0).getPerson().getId());
		assertEquals(order.getAwaitingTime(), orderEntities.get(0).getAwaitingTime());
		assertEquals(order.getTotalPrice(), orderEntities.get(0).getTotalPrice());
	}

}