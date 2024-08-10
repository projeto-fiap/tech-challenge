package tech.fiap.project.app.service.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.OrderMapper;
import tech.fiap.project.app.dto.OrderDTO;
import tech.fiap.project.domain.entity.*;
import tech.fiap.project.domain.usecase.order.CreateOrUpdateOrderUseCase;
import tech.fiap.project.domain.usecase.order.RetrieveOrderUseCase;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CheckoutOrderService {

	private CreateOrUpdateOrderUseCase createOrUpdateOrderUsecase;

	private RetrieveOrderUseCase retrieveOrderUseCase;

	public Optional<OrderDTO> execute(Long id) {
		Optional<Order> order = retrieveOrderUseCase.findById(id);
		if (order.isPresent() && order.get().getStatus().equals(OrderStatus.AWAITING_PAYMENT)) {
			order.ifPresent(this::setPaid);
			order.ifPresent(this::saveOrder);
			return order.map(OrderMapper::toDTO);
		}
		return Optional.empty();
	}

	private void saveOrder(Order order) {
		createOrUpdateOrderUsecase.execute(order);
	}

	private void setPaid(Order order) {
		var now = LocalDateTime.now();
		var currency = Currency.getInstance("BRL");
		var payment = new Payment(now, "PIX", order.getTotalPrice(), currency);
		order.setPayment(payment);
		order.setStatus(OrderStatus.PAID);
	}

}
