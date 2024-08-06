package tech.fiap.project.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.fiap.project.app.dto.KitchenDTO;
import tech.fiap.project.app.dto.OrderDTO;
import tech.fiap.project.app.service.kitchen.KitchenService;
import tech.fiap.project.app.service.order.CheckoutOrderService;
import tech.fiap.project.app.service.order.CreateOrderService;
import tech.fiap.project.app.service.order.EndOrderService;
import tech.fiap.project.app.service.order.RetrieveOrderService;

import java.awt.image.BufferedImage;
import java.util.List;

@RestController
@RequestMapping("api/v1/kitchen")
@AllArgsConstructor
public class KitchenController {

	private KitchenService kitchenService;

	@GetMapping
	public ResponseEntity<List<KitchenDTO>> retrieveOrders() {
		return ResponseEntity.ok(kitchenService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<KitchenDTO> retrieveOrderById(@PathVariable Long id) {
		return kitchenService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

}
