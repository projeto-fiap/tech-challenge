package tech.fiap.project.infra.dataprovider;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.domain.dataprovider.OrderDataProvider;
import tech.fiap.project.domain.dataprovider.PaymentDataProvider;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.Payment;
import tech.fiap.project.infra.entity.OrderEntity;
import tech.fiap.project.infra.mapper.OrderRepositoryMapper;
import tech.fiap.project.infra.mapper.PaymentRepositoryMapper;
import tech.fiap.project.infra.repository.OrderRepository;
import tech.fiap.project.infra.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentDataProviderImpl implements PaymentDataProvider {

	private PaymentRepository paymentRepository;

	@Override
	public List<Payment> retrieveAll() {
		return paymentRepository.findAll().stream().map(PaymentRepositoryMapper::toDomainWithOrder).toList();
	}

	@Override
	public Optional<Payment> retrieveById(Long id) {
		return paymentRepository.findById(id).map(PaymentRepositoryMapper::toDomainWithoutOrder);
	}

}
