package tech.fiap.project.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.fiap.project.app.dto.OrderDTO;
import tech.fiap.project.app.service.CreateOrderService;

@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private CreateOrderService createOrderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrUpdate(@RequestBody OrderDTO orderDTO) {
        OrderDTO orderCreated = createOrderService.execute(orderDTO);
        return ResponseEntity.ok(orderCreated);
    }

}
