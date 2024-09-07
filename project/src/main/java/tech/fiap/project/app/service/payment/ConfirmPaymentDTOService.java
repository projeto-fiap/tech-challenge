package tech.fiap.project.app.service.payment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.PaymentMapper;
import tech.fiap.project.app.dto.PaymentDTO;
import tech.fiap.project.domain.usecase.payment.ConfirmPaymentUseCase;

@AllArgsConstructor
@Service
public class ConfirmPaymentDTOService {

	private ConfirmPaymentUseCase confirmPaymentUseCase;

	public PaymentDTO confirmPayment(Long orderId) {
		return PaymentMapper.toDomain(confirmPaymentUseCase.confirmPayment(orderId));
	}

}
