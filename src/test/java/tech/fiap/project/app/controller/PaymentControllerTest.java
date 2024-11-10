package tech.fiap.project.app.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tech.fiap.project.app.dto.ConfirmPaymentDTO;
import tech.fiap.project.app.dto.PaymentDTO;
import tech.fiap.project.app.service.payment.RetrievePaymentService;
import tech.fiap.project.app.service.payment.ConfirmPaymentDTOService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PaymentControllerTest {

	@Mock
	private RetrievePaymentService retrievePaymentService;

	@Mock
	private ConfirmPaymentDTOService confirmPaymentDTOService;

	@InjectMocks
	private PaymentController paymentController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void confirmPayment_shouldReturnPaymentDTO_whenSuccessful() {
		ConfirmPaymentDTO confirmPaymentDTO = new ConfirmPaymentDTO();
		PaymentDTO paymentDTO = new PaymentDTO();

		when(confirmPaymentDTOService.confirmPayment(confirmPaymentDTO)).thenReturn(paymentDTO);

		ResponseEntity<PaymentDTO> response = paymentController.confirmPayment(confirmPaymentDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(paymentDTO, response.getBody());
	}

	@Test
	void listAll_shouldReturnListOfPaymentDTO_whenSuccessful() {
		List<PaymentDTO> payments = List.of(new PaymentDTO());

		when(retrievePaymentService.findAll()).thenReturn(payments);

		ResponseEntity<List<PaymentDTO>> response = paymentController.listAll();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(payments, response.getBody());
	}

}