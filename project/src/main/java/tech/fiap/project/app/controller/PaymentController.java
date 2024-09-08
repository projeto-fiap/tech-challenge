package tech.fiap.project.app.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.fiap.project.app.dto.ConfirmPaymentDTO;
import tech.fiap.project.app.dto.PaymentDTO;
import tech.fiap.project.app.service.payment.RetrievePaymentService;
import tech.fiap.project.app.service.payment.ConfirmPaymentDTOService;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
@Validated
@RequiredArgsConstructor
public class PaymentController {

	private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

	private final RetrievePaymentService retrievePaymentService;

	private final ConfirmPaymentDTOService confirmPaymentDTOService;

	@PostMapping("/confirm/mock")
	public ResponseEntity<PaymentDTO> confirmPayment(@RequestBody @Validated ConfirmPaymentDTO confirmPaymentDTO) {
		log.info("Received request to create items: {}", confirmPaymentDTO);
		PaymentDTO paymentDTO = confirmPaymentDTOService.confirmPayment(confirmPaymentDTO);
		log.info("Items created successfully: {}", paymentDTO);
		return ResponseEntity.status(HttpStatus.OK).body(paymentDTO);
	}

	@GetMapping
	public ResponseEntity<List<PaymentDTO>> listAll() {
		List<PaymentDTO> payments = retrievePaymentService.findAll();
		log.info("Items created successfully: {}", payments);
		return ResponseEntity.status(HttpStatus.OK).body(payments);
	}

}
