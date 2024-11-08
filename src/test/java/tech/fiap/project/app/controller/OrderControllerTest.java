package tech.fiap.project.app.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import tech.fiap.project.app.dto.KitchenDTO;
import tech.fiap.project.app.dto.OrderRequestDTO;
import tech.fiap.project.app.dto.OrderResponseDTO;
import tech.fiap.project.app.service.kitchen.KitchenService;
import tech.fiap.project.app.service.order.*;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class OrderControllerTest {

	@Mock
	private CreateOrderService createOrderService;

	@Mock
	private RetrieveOrderService retrieveOrderService;

	@Mock
	private EndOrderService endOrderService;

	@Mock
	private CheckoutOrderService checkoutOrderService;

	@Mock
	private KitchenService kitchenService;

	@Mock
	private DeliverOrderService deliverOrderService;

	@InjectMocks
	private OrderController orderController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void createOrUpdate_shouldReturnCreatedOrder_whenSuccessful() {
		OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

		when(createOrderService.execute(orderRequestDTO)).thenReturn(orderResponseDTO);

		ResponseEntity<OrderResponseDTO> response = orderController.createOrUpdate(orderRequestDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(orderResponseDTO, response.getBody());
	}

	@Test
	void retrieveOrders_shouldReturnListOfOrders_whenSuccessful() {
		List<OrderResponseDTO> orders = List.of(new OrderResponseDTO());

		when(retrieveOrderService.findAll()).thenReturn(orders);

		ResponseEntity<List<OrderResponseDTO>> response = orderController.retrieveOrders();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(orders, response.getBody());
	}

	@Test
	void retrieveOrderById_shouldReturnOrder_whenFound() {
		Long id = 1L;
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

		when(retrieveOrderService.findById(id)).thenReturn(Optional.of(orderResponseDTO));

		ResponseEntity<OrderResponseDTO> response = orderController.retrieveOrderById(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(orderResponseDTO, response.getBody());
	}

	@Test
	void retrieveOrderById_shouldReturnNotFound_whenNotFound() {
		Long id = 1L;

		when(retrieveOrderService.findById(id)).thenReturn(Optional.empty());

		ResponseEntity<OrderResponseDTO> response = orderController.retrieveOrderById(id);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void endOrder_shouldReturnQRCode_whenSuccessful() {
		Long id = 1L;
		BufferedImage qrcode = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

		when(endOrderService.execute(id)).thenReturn(qrcode);

		ResponseEntity<BufferedImage> response = orderController.endOrder(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(qrcode, response.getBody());
	}

	@Test
	void checkout_shouldReturnOrder_whenSuccessful() {
		Long id = 1L;
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
		KitchenDTO kitchenDTO = new KitchenDTO();

		when(checkoutOrderService.execute(id)).thenReturn(Optional.of(orderResponseDTO));
		when(kitchenService.create(orderResponseDTO)).thenReturn(Optional.of(kitchenDTO));

		ResponseEntity<OrderResponseDTO> response = orderController.checkout(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(orderResponseDTO, response.getBody());
	}

	@Test
	void checkout_shouldReturnNotFound_whenOrderNotFound() {
		Long id = 1L;

		when(checkoutOrderService.execute(id)).thenReturn(Optional.empty());

		ResponseEntity<OrderResponseDTO> response = orderController.checkout(id);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void ongoingOrders_shouldReturnListOfOngoingOrders_whenSuccessful() {
		List<OrderResponseDTO> orders = List.of(new OrderResponseDTO());

		when(retrieveOrderService.findOngoingAll()).thenReturn(orders);

		ResponseEntity<List<OrderResponseDTO>> response = orderController.ongoingOrders();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(orders, response.getBody());
	}

	@Test
	void deliver_shouldReturnDeliveredOrder_whenSuccessful() {
		Long id = 1L;
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

		when(deliverOrderService.execute(id)).thenReturn(orderResponseDTO);

		ResponseEntity<OrderResponseDTO> response = orderController.deliver(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(orderResponseDTO, response.getBody());
	}

}