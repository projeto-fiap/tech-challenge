package tech.fiap.project.domain.dataprovider;

import tech.fiap.project.domain.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentDataProvider {

	List<Payment> retrieveAll();

	Optional<Payment> retrieveById(Long id);

}
