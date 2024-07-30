package tech.fiap.project.infra.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.usecase.CreatePaymentUrlUseCase;
import tech.fiap.project.domain.usecase.impl.order.CalculateTotalOrderUseCaseImpl;
import tech.fiap.project.infra.configuration.MercadoPagoConstants;
import tech.fiap.project.infra.configuration.MercadoPagoProperties;
import tech.fiap.project.app.dto.CashOutDTO;
import tech.fiap.project.app.dto.ItemMercadoLivreDTO;
import tech.fiap.project.app.dto.PaymentRequestDTO;
import tech.fiap.project.app.dto.PaymentResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CreatePaymentUrlUseCaseMercadoPagoService implements CreatePaymentUrlUseCase {

	private RestTemplate restTemplateMercadoPago;

	private MercadoPagoProperties mercadoPagoProperties;

	private CalculateTotalOrderUseCaseImpl calculateTotalOrderUseCaseImpl;

	@Override
	public String execute(Order order) {
		String url = MercadoPagoConstants.BASE_URI + buildBaseUrl();
		HttpHeaders headers = getHttpHeaders();
		Long id = order.getId();
		PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO(new CashOutDTO(0),
				buildDescription(id), String.format("urn:order:id:%s",id),
				buildItems(order.getItems()), null,
				buildDescription(id), calculateTotalOrderUseCaseImpl.execute(order.getItems()));
		RequestEntity<PaymentRequestDTO> body = RequestEntity.post(url).headers(headers).body(paymentRequestDTO);
		ResponseEntity<PaymentResponseDTO> exchange = restTemplateMercadoPago.exchange(body, PaymentResponseDTO.class);
		return Objects.requireNonNull(exchange.getBody()).getQrData();
	}

	private String buildDescription(Long orderId) {
		return "Pagamento do pedido " + orderId;
	}

	private List<ItemMercadoLivreDTO> buildItems(List<Item> items) {
		ArrayList<ItemMercadoLivreDTO> itemMercadoLivreDTOS = new ArrayList<>();
		items.forEach(item -> {
			ItemMercadoLivreDTO itemMercadoLivreDTO = new ItemMercadoLivreDTO(item.getId().toString(), "marketplace",
					item.getName(), item.getDescription(), calculateTotalOrderUseCaseImpl.execute(items), item.getQuantity().intValue(),
					item.getUnit(),  calculateTotalOrderUseCaseImpl.execute(items));
			itemMercadoLivreDTOS.add(itemMercadoLivreDTO);
		});
		return itemMercadoLivreDTOS;
	}

	private HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		String token = mercadoPagoProperties.getAccessToken();
		headers.add("Authorization", "Bearer  " + token);
		return headers;
	}

	private String buildBaseUrl() {
		String userId = mercadoPagoProperties.getUserId();
		String pos = mercadoPagoProperties.getPos();
		return String.format(MercadoPagoConstants.BASE_PAYMENT_METHOD, userId, pos);
	}

}
