package tech.fiap.project.domain.usecase.impl.order;

import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.usecase.CreateQrCodeUseCase;
import tech.fiap.project.domain.usecase.order.CreateOrUpdateOrderUseCase;
import tech.fiap.project.domain.usecase.order.EndOrderUseCase;
import tech.fiap.project.domain.usecase.order.RetrieveOrderUseCase;
import tech.fiap.project.infra.exception.OrderNotFound;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

import static tech.fiap.project.domain.entity.OrderStatus.AWAITING_PAYMENT;

public class EndOrderUseCaseImpl implements EndOrderUseCase {

	private final CreateOrUpdateOrderUseCase createOrUpdateOrderUsecase;

	private final RetrieveOrderUseCase retrieveOrderUseCase;

	private final CreateQrCodeUseCase generateQrCode;

	public EndOrderUseCaseImpl(CreateOrUpdateOrderUseCase createOrUpdateOrderUsecase,
			RetrieveOrderUseCase retrieveOrderUseCase, CreateQrCodeUseCase generateQrCode) {
		this.createOrUpdateOrderUsecase = createOrUpdateOrderUsecase;
		this.retrieveOrderUseCase = retrieveOrderUseCase;
		this.generateQrCode = generateQrCode;
	}

	public BufferedImage execute(Long id) throws IOException {
		Order order = updateOrder(id);
		return generateQrCode.execute(order);
	}

	private Order updateOrder(Long id) {
		Optional<Order> orderSaved = retrieveOrderUseCase.findById(id);
		if (orderSaved.isPresent()) {
			Order order = orderSaved.get();
			order.setStatus(AWAITING_PAYMENT);
			return createOrUpdateOrderUsecase.execute(order);
		}
		else {
			throw new OrderNotFound(id);
		}
	}

}
