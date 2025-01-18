package tech.fiap.project.app.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.fiap.project.app.dto.KitchenDTO;
import tech.fiap.project.app.dto.OrderRequestDTO;
import tech.fiap.project.app.dto.OrderResponseDTO;
import tech.fiap.project.app.service.kitchen.KitchenService;
import tech.fiap.project.app.service.order.*;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderController {

	private CreateOrderService createOrderService;

	private RetrieveOrderService retrieveOrderService;

	private EndOrderService endOrderService;

	private CheckoutOrderService checkoutOrderService;

	private KitchenService kitchenService;

	private DeliverOrderService deliverOrderService;

	@PostMapping
	public ResponseEntity<OrderResponseDTO> createOrUpdate(@RequestBody OrderRequestDTO orderRequestDTO) {
		OrderResponseDTO orderCreated = createOrderService.execute(orderRequestDTO);
		return ResponseEntity.ok(orderCreated);
	}

	@GetMapping
	public ResponseEntity<List<OrderResponseDTO>> retrieveOrders() {
		return ResponseEntity.ok(retrieveOrderService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderResponseDTO> retrieveOrderById(@PathVariable Long id) {
		return retrieveOrderService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@SneakyThrows
    @PutMapping(value = "/endOrder/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> endOrder(@PathVariable Long id) {
		BufferedImage qrcode = endOrderService.execute(id);
		return ResponseEntity.ok(qrcode);
	}

	@PutMapping(value = "/checkout/{id}")
	public ResponseEntity<OrderResponseDTO> checkout(@PathVariable Long id) {
		Optional<OrderResponseDTO> paidOrder = checkoutOrderService.execute(id);
		if (paidOrder.isPresent()) {
			Optional<KitchenDTO> kitchenQueue = kitchenService.create(paidOrder.get());
			kitchenQueue.ifPresent(kitchenDTO -> paidOrder.get().setKitchenQueue(kitchenDTO));
			return paidOrder.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping(value = "/ongoing/orders")
	public ResponseEntity<List<OrderResponseDTO>> ongoingOrders() {
		return ResponseEntity.ok(retrieveOrderService.findOngoingAll());
	}

	@PutMapping(value = "/deliver/{id}")
	public ResponseEntity<OrderResponseDTO> deliver(@PathVariable Long id) {
		OrderResponseDTO deliveredOrder = deliverOrderService.execute(id);
		return ResponseEntity.ok(deliveredOrder);
	}

}
