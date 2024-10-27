package tech.fiap.project.app.service.payment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.PaymentMapper;
import tech.fiap.project.app.dto.PaymentDTO;
import tech.fiap.project.domain.usecase.payment.RetrievePaymentUseCase;

import java.util.List;

@AllArgsConstructor
@Service
public class RetrievePaymentService {

	private RetrievePaymentUseCase retrievePaymentUseCase;

	public List<PaymentDTO> findAll() {
		return retrievePaymentUseCase.findAll().stream().map(PaymentMapper::toDomain).toList();
	}

}
