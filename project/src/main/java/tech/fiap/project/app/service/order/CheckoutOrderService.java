package tech.fiap.project.app.service.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.OrderMapper;
import tech.fiap.project.app.dto.OrderResponseDTO;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.usecase.order.RetrieveOrderUseCase;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CheckoutOrderService {

	private RetrieveOrderUseCase retrieveOrderUseCase;

	public Optional<OrderResponseDTO> execute(Long id) {
		Optional<Order> order = retrieveOrderUseCase.findByIdWithPayment(id);
		if (order.isPresent() && order.get().getStatus().equals(OrderStatus.PAID)) {
			return order.map(OrderMapper::toDTO);
		}
		return Optional.empty();
	}

}
