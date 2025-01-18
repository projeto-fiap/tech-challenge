package tech.fiap.project.app.service.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.app.dto.ItemRequestDTO;
import tech.fiap.project.app.dto.OrderRequestDTO;
import tech.fiap.project.app.dto.OrderResponseDTO;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.usecase.order.CreateOrUpdateOrderUseCase;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateOrderServiceTest {

	@Mock
	private CreateOrUpdateOrderUseCase createOrUpdateOrderUsecase;

	@InjectMocks
	private CreateOrderService createOrderService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void execute_shouldReturnOrderResponseDTO() {
		OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
		Item item1 = new Item(1L, "Item 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(2), "unit", ItemCategory.FOOD,
				new ArrayList<>(), "description", "imageUrl");
		Item item2 = new Item(2L, "Item 2", BigDecimal.valueOf(5.00), BigDecimal.valueOf(3), "unit", ItemCategory.DRINK,
				new ArrayList<>(), "description", "imageUrl");
		List<Item> items = Arrays.asList(item1, item2);
		ItemRequestDTO itemRequestDTO = new ItemRequestDTO();
		itemRequestDTO.setId(2L);
		itemRequestDTO.setQuantity(BigDecimal.valueOf(3));
		itemRequestDTO.setUnit("unit");

		List<ItemRequestDTO> itemRequestDTOS = Arrays.asList(itemRequestDTO);
		orderRequestDTO.setItems(itemRequestDTOS);

		Order order = new Order(1L, OrderStatus.AWAITING_PAYMENT, LocalDateTime.now(), LocalDateTime.now(), items, null,
				null, BigDecimal.TEN);
		OrderResponseDTO expectedOrderResponseDTO = new OrderResponseDTO();
		expectedOrderResponseDTO.setId(1L);
		when(createOrUpdateOrderUsecase.execute(any())).thenReturn(order);
		OrderResponseDTO result = createOrderService.execute(orderRequestDTO);
		assertNotNull(result);
	}

}