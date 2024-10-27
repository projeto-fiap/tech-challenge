package tech.fiap.project.app.service.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.KitchenMapper;
import tech.fiap.project.app.adapter.OrderMapper;
import tech.fiap.project.app.dto.KitchenDTO;
import tech.fiap.project.app.dto.OrderResponseDTO;
import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.domain.entity.KitchenStatus;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.usecase.kitchen.KitchenRetrieveUseCase;
import tech.fiap.project.domain.usecase.order.DeliverOrderUseCase;
import tech.fiap.project.domain.usecase.order.EndOrderUseCase;
import tech.fiap.project.domain.usecase.order.RetrieveOrderUseCase;
import tech.fiap.project.infra.exception.KitchenStatusException;
import tech.fiap.project.infra.exception.OrderNotFound;
import tech.fiap.project.infra.exception.OrderStatusException;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeliverOrderService {

	private DeliverOrderUseCase deliverOrderUseCase;
	private RetrieveOrderUseCase retrieveOrderUseCase;
	private KitchenRetrieveUseCase kitchenRetrieveUseCase;

	public OrderResponseDTO execute(Long id) {
		return OrderMapper.toResponse(deliverOrder(id));
	}

	private Order deliverOrder(Long id) {
		Optional<Kitchen> kitchen = kitchenRetrieveUseCase.findById(id);
		Optional<OrderResponseDTO> orderDTO = retrieveOrderUseCase.findByIdWithPayment(id).map(_order -> OrderMapper.toDTO(_order, kitchen));

		if (orderDTO.isPresent()) {
			var safeOrder = orderDTO.get();
			var safeKitchen = safeOrder.getKitchenQueue();

			if (safeKitchen.getStatus() != KitchenStatus.DONE) {
				throw new KitchenStatusException(id);
			}

			if (safeOrder.getStatus() == OrderStatus.FINISHED) {
				throw new OrderStatusException(id);
			}

			return deliverOrderUseCase.execute(id);
		}
		throw new OrderNotFound(id);
	}

}