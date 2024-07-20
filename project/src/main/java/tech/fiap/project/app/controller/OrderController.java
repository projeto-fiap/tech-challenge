package tech.fiap.project.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.fiap.project.app.dto.OrderDTO;
import tech.fiap.project.app.service.CreateOrderService;
import tech.fiap.project.app.service.RetrieveOrderService;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderController {

	private CreateOrderService createOrderService;

	private RetrieveOrderService retrieveOrderService;

	@PostMapping
	public ResponseEntity<OrderDTO> createOrUpdate(@RequestBody OrderDTO orderDTO) {
		OrderDTO orderCreated = createOrderService.execute(orderDTO);
		return ResponseEntity.ok(orderCreated);
	}

	@GetMapping
	public ResponseEntity<List<OrderDTO>> retrieveOrders() {
		return ResponseEntity.ok(retrieveOrderService.findAll());
	}

}
