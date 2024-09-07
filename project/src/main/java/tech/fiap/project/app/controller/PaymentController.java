package tech.fiap.project.app.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.fiap.project.app.dto.ConfirmPaymentDTO;
import tech.fiap.project.app.dto.PaymentDTO;
import tech.fiap.project.app.service.payment.ConfirmPaymentDTOService;

@RestController
@RequestMapping("api/v1/payment")
@Validated
@RequiredArgsConstructor
public class PaymentController {

	private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

	private final ConfirmPaymentDTOService confirmPaymentDTOService;

	@PostMapping("/confirm/mock")
	public ResponseEntity<PaymentDTO> confirmPayment(@RequestBody @Validated ConfirmPaymentDTO confirmPaymentDTO) {
		try {
			log.info("Received request to create items: {}", confirmPaymentDTO);
			PaymentDTO paymentDTO = confirmPaymentDTOService.confirmPayment(confirmPaymentDTO.getOrder().getId());
			log.info("Items created successfully: {}", paymentDTO);
			return ResponseEntity.status(HttpStatus.OK).body(paymentDTO);
		}
		catch (Exception e) {
			log.error("Error creating items", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
