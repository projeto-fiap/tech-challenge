
package tech.fiap.project.domain.usecase.impl.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.usecase.CreateQrCodeUseCase;
import tech.fiap.project.domain.usecase.order.CreateOrUpdateOrderUseCase;
import tech.fiap.project.domain.usecase.order.RetrieveOrderUseCase;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class EndOrderUseCaseImplTest {

	@Mock
	private CreateOrUpdateOrderUseCase createOrUpdateOrderUsecase;

	@Mock
	private RetrieveOrderUseCase retrieveOrderUseCase;

	@Mock
	private CreateQrCodeUseCase generateQrCode;

	@InjectMocks
	private EndOrderUseCaseImpl endOrderUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void execute_updatesOrderAndGeneratesQrCodeSuccessfully() {
		Order order = new Order(1L, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Collections.emptyList(), Duration.ZERO, null, BigDecimal.ZERO);
		BufferedImage qrCode = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

		when(retrieveOrderUseCase.findById(1L)).thenReturn(Optional.of(order));
		when(createOrUpdateOrderUsecase.execute(order)).thenReturn(order);
		when(generateQrCode.execute(order)).thenReturn(qrCode);

		BufferedImage result = endOrderUseCaseImpl.execute(1L);

		assertEquals(qrCode, result);
		assertEquals(OrderStatus.AWAITING_PAYMENT, order.getStatus());
	}

}
