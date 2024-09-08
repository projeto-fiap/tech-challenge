package tech.fiap.project.app.service.payment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.PaymentMapper;
import tech.fiap.project.app.dto.ConfirmPaymentDTO;
import tech.fiap.project.app.dto.PaymentDTO;
import tech.fiap.project.domain.usecase.payment.RejectPaymentUseCase;
import tech.fiap.project.domain.usecase.payment.ConfirmPaymentUseCase;

import static tech.fiap.project.app.dto.StatePayment.ACCEPTED;

@AllArgsConstructor
@Service
public class ConfirmPaymentDTOService {

	private ConfirmPaymentUseCase confirmPaymentUseCase;
	private RejectPaymentUseCase rejectPaymentUseCase;

	public PaymentDTO confirmPayment(ConfirmPaymentDTO confirmPaymentDTO) {
		if (confirmPaymentDTO.getState().equals(ACCEPTED)) {
		return PaymentMapper.toDomain(confirmPaymentUseCase.confirmPayment(confirmPaymentDTO.getOrder().getId()));
		}else {
			return PaymentMapper.toDomain(rejectPaymentUseCase.rejectPayment(confirmPaymentDTO.getOrder().getId()));
		}
	}

}
