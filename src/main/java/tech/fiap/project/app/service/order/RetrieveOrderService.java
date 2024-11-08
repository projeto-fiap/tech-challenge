package tech.fiap.project.app.service.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.OrderMapper;
import tech.fiap.project.app.dto.KitchenDTO;
import tech.fiap.project.app.dto.OrderRequestDTO;
import tech.fiap.project.app.dto.OrderResponseDTO;
import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.domain.entity.KitchenStatus;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.usecase.kitchen.KitchenRetrieveUseCase;
import tech.fiap.project.domain.usecase.kitchen.KitchenUpdateUseCase;
import tech.fiap.project.domain.usecase.order.RetrieveOrderUseCase;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RetrieveOrderService {

	private RetrieveOrderUseCase retrieveOrderUseCase;

	private KitchenRetrieveUseCase kitchenRetrieveUseCase;

	public List<OrderResponseDTO> findAll() {
		List<OrderResponseDTO> dto = OrderMapper.toDTO(retrieveOrderUseCase.findAll());
		dto.forEach(this::setDuration);
		return dto;
	}

	public Optional<OrderResponseDTO> findById(Long id) {
		Optional<OrderResponseDTO> orderDTO = retrieveOrderUseCase.findByIdWithPayment(id).map(OrderMapper::toDTO);
		orderDTO.ifPresent(this::setDuration);
		return orderDTO;
	}

	public List<OrderResponseDTO> findOngoingAll() {
		List<Kitchen> kitchenDto = kitchenRetrieveUseCase.findAll();
		List<Long> kichenIds = kitchenDto.stream().map(_kitchen -> _kitchen.getOrderId()).toList();
		List<OrderResponseDTO> dto = OrderMapper.toDTO(retrieveOrderUseCase.findAllById(kichenIds), kitchenDto);

		return sortByStatusThanDate(dto);
	}

	private void setDuration(OrderResponseDTO order) {
		long seconds = Duration.between(order.getCreatedDate(), LocalDateTime.now()).getSeconds();
		order.setAwaitingTime(Duration.of(seconds, ChronoUnit.SECONDS));
	}

	private List<OrderResponseDTO> sortByStatusThanDate(List<OrderResponseDTO> listOrder) {
		List<OrderResponseDTO> filteredListOrder = filterOngoingListOrder(listOrder);
		return sortOngoingListOrder(filteredListOrder);
	}

	private List<OrderResponseDTO> filterOngoingListOrder(List<OrderResponseDTO> listOrder) {
		return listOrder.stream()
				.filter((order) -> (order.getKitchenQueue() != null && order.getStatus() != OrderStatus.FINISHED))
				.collect(Collectors.toList());
	}

	private List<OrderResponseDTO> sortOngoingListOrder(List<OrderResponseDTO> listOrder) {
		listOrder.sort((order1, order2) -> {
			int statusComparison = priorityCompare(order1, order2);
			if (statusComparison != 0) {
				return statusComparison;
			}
			return order1.getUpdatedDate().compareTo(order2.getUpdatedDate());
		});
		return listOrder;
	}

	private Integer priorityCompare(OrderResponseDTO firstOrder, OrderResponseDTO secondOrder) {
		Map<KitchenStatus, Integer> priorityStatus = getPriorityOrderStatus();
		KitchenStatus firstOrderStatus = firstOrder.getKitchenQueue().getStatus();
		KitchenStatus secondOrderStatus = secondOrder.getKitchenQueue().getStatus();
		return priorityStatus.get(firstOrderStatus).compareTo(priorityStatus.get(secondOrderStatus));
	}

	private Map<KitchenStatus, Integer> getPriorityOrderStatus() {
		Map<KitchenStatus, Integer> statusOrder = new HashMap<>();
		statusOrder.put(KitchenStatus.AWAITING_PRODUCTION, 1);
		statusOrder.put(KitchenStatus.IN_PRODUCTION, 2);
		statusOrder.put(KitchenStatus.DONE, 3);
		return statusOrder;
	}

}
