package tech.fiap.project.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.fiap.project.app.dto.OrderRequestDTO;
import tech.fiap.project.app.dto.OrderResponseDTO;
import tech.fiap.project.app.service.kitchen.KitchenService;
import tech.fiap.project.app.service.order.CheckoutOrderService;
import tech.fiap.project.app.service.order.CreateOrderService;
import tech.fiap.project.app.service.order.EndOrderService;
import tech.fiap.project.app.service.order.RetrieveOrderService;

import java.awt.image.BufferedImage;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderController {

	private CreateOrderService createOrderService;

	private RetrieveOrderService retrieveOrderService;

	private EndOrderService endOrderService;

	private CheckoutOrderService checkoutOrderService;

	private KitchenService kitchenService;

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

	@PutMapping(value = "/endOrder/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> endOrder(@PathVariable Long id) {
		BufferedImage qrcode = endOrderService.execute(id);
		return ResponseEntity.ok(qrcode);
	}

	@PutMapping(value = "/checkout/{id}")
	public ResponseEntity<OrderResponseDTO> checkout(@PathVariable Long id) {
		var paidOrder = checkoutOrderService.execute(id);
		if (paidOrder.isPresent()) {
			var kitchenQueue = kitchenService.create(paidOrder.get());
			kitchenQueue.ifPresent(kitchenDTO -> paidOrder.get().setKitchenQueue(kitchenDTO));
			return paidOrder.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		}
		return ResponseEntity.notFound().build();
	}

}
