package tech.fiap.project.domain.usecase.impl.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.usecase.order.CreateOrUpdateOrderUseCase;
import tech.fiap.project.domain.usecase.order.RetrieveOrderUseCase;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DeliverOrderUseCaseImplTest {

	@Mock
	private CreateOrUpdateOrderUseCase createOrUpdateOrderUsecase;

	@Mock
	private RetrieveOrderUseCase retrieveOrderUseCase;

	@InjectMocks
	private DeliverOrderUseCaseImpl deliverOrderUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void execute_deliversOrderSuccessfully() {
		Order order = new Order(1L, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Collections.emptyList(), Duration.ZERO, null, BigDecimal.ZERO);

		when(retrieveOrderUseCase.findByIdWithPayment(1L)).thenReturn(Optional.of(order));
		when(createOrUpdateOrderUsecase.execute(order)).thenReturn(order);

		Order result = deliverOrderUseCaseImpl.execute(1L);

		assertEquals(OrderStatus.FINISHED, result.getStatus());
	}

}