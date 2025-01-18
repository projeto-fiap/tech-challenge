package tech.fiap.project.infra.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriTemplateHandler;
import tech.fiap.project.domain.dataprovider.ItemDataProvider;
import tech.fiap.project.domain.dataprovider.KitchenDataProvider;
import tech.fiap.project.domain.dataprovider.OrderDataProvider;
import tech.fiap.project.domain.dataprovider.PersonDataProvider;
import tech.fiap.project.domain.usecase.CreateQrCodeUseCase;
import tech.fiap.project.domain.usecase.impl.CreateQrCodeUseCaseImpl;
import tech.fiap.project.domain.usecase.impl.item.*;
import tech.fiap.project.domain.usecase.impl.kitchen.KitchenCreateUseCaseImpl;
import tech.fiap.project.domain.usecase.impl.kitchen.KitchenRetrieveUseCaseImpl;
import tech.fiap.project.domain.usecase.impl.kitchen.KitchenUpdateUseCaseImpl;
import tech.fiap.project.domain.usecase.impl.order.CalculateTotalOrderUseCaseImpl;
import tech.fiap.project.domain.usecase.impl.order.DeliverOrderUseCaseImpl;
import tech.fiap.project.domain.usecase.impl.order.EndOrderUseCaseImpl;
import tech.fiap.project.domain.usecase.impl.order.RetrieveOrderUseCaseImpl;
import tech.fiap.project.domain.usecase.impl.person.*;
import tech.fiap.project.domain.usecase.item.DeleteItemUseCase;
import tech.fiap.project.domain.usecase.item.InitializeItemUseCase;
import tech.fiap.project.domain.usecase.kitchen.KitchenCreateUseCase;
import tech.fiap.project.domain.usecase.kitchen.KitchenRetrieveUseCase;
import tech.fiap.project.domain.usecase.kitchen.KitchenUpdateUseCase;
import tech.fiap.project.domain.usecase.order.CreateOrUpdateOrderUseCase;
import tech.fiap.project.domain.usecase.order.DeliverOrderUseCase;
import tech.fiap.project.domain.usecase.order.EndOrderUseCase;
import tech.fiap.project.domain.usecase.person.InitializePersonUseCase;

import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@org.springframework.context.annotation.Configuration
@Getter
@ComponentScan("tech.fiap.project")
@Setter
public class Configuration {

	@Value("${tech-challenge.payments.base-url}")
	String paymentsBaseUrl;

	@Value("${keycloak.base-url}")
	String keycloakBaseUrl;

	@Value("${tech-challenge.client-id}")
	String paymentsClientId;

	@Value("${tech-challenge.client-secret}")
	String paymentsClientSecret;

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
		objectMapper.setDateFormat(df);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	}

	@Bean(name = "restTemplatePayments")
	public RestTemplate restTemplatePayments() {
		UriTemplateHandler uriTemplateHandler = new DefaultUriBuilderFactory(paymentsBaseUrl);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setUriTemplateHandler(uriTemplateHandler);
		return restTemplate;
	}

	@Bean
	public RestTemplate restTemplateKeycloak() {
        return new RestTemplate();
	}

	@Bean
	public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
		return new BufferedImageHttpMessageConverter();
	}

	@Bean
	public InitializePersonUseCaseImpl initializePersonUseCase(PersonDataProvider personDataProvider) {
		return new InitializePersonUseCaseImpl(personDataProvider);
	}

	@Bean
	public RetrieveItemUseCaseImpl retrieveItemUseCase(ItemDataProvider itemDataProvider) {
		return new RetrieveItemUseCaseImpl(itemDataProvider);
	}

	@Bean
	public CreateItemUseCaseImpl createItemUseCase(ItemDataProvider itemDataProvider) {
		return new CreateItemUseCaseImpl(itemDataProvider);
	}

	@Bean
	public RetrieveOrderUseCaseImpl retrieveOrderUseCase(OrderDataProvider orderDataProvider) {
		return new RetrieveOrderUseCaseImpl(orderDataProvider);
	}

	@Bean
	public RetrievePersonUseCaseImpl retrievePersonUseCase(PersonDataProvider personDataProvider) {
		return new RetrievePersonUseCaseImpl(personDataProvider);
	}

	@Bean
	public UpdatePersonUseCaseImpl updatePersonUseCase(PersonDataProvider personDataProvider) {
		return new UpdatePersonUseCaseImpl(personDataProvider);
	}

	@Bean
	public SavePersonUseCaseImpl savePersonUseCase(PersonDataProvider personDataProvider) {
		return new SavePersonUseCaseImpl(personDataProvider);
	}

	@Bean
	public DeletePersonUseCaseImpl deletePersonUseCase(PersonDataProvider personDataProvider) {
		return new DeletePersonUseCaseImpl(personDataProvider);
	}

	@Bean
	public InitializeItemUseCase initializeItemUseCase(ItemDataProvider itemDataProvider) {
		return new InitializeItemUseCaseImpl(itemDataProvider);
	}

	@Bean
	public CreateQrCodeUseCaseImpl createQrCodeUseCase(RestTemplate restTemplatePayments,RestTemplate restTemplateKeycloak) {
		return new CreateQrCodeUseCaseImpl(restTemplatePayments, restTemplateKeycloak, keycloakBaseUrl, paymentsClientId,paymentsClientSecret);
	}

	@Bean
	public CalculateTotalOrderUseCaseImpl calculateTotalOrderUseCase() {
		return new CalculateTotalOrderUseCaseImpl();
	}

	@Bean
	public EndOrderUseCase endOrderUseCase(CreateOrUpdateOrderUseCase createOrUpdateOrderUseCase,
			RetrieveOrderUseCaseImpl retrieveOrderUseCase, CreateQrCodeUseCase createQrCodeUseCase) {
		return new EndOrderUseCaseImpl(createOrUpdateOrderUseCase, retrieveOrderUseCase, createQrCodeUseCase);
	}

	@Bean
	public CreateOrUpdateOrderUseCaseImpl createOrUpdateOrderUseCaseImpl(
			InitializeItemUseCase initializeItemUseCaseImpl, OrderDataProvider orderDataProvider,
			InitializePersonUseCase initializePersonUseCase,
			CalculateTotalOrderUseCaseImpl calculateTotalOrderUseCase) {
		return new CreateOrUpdateOrderUseCaseImpl(orderDataProvider, initializePersonUseCase, initializeItemUseCaseImpl,
				calculateTotalOrderUseCase);
	}

	@Bean
	public DeliverOrderUseCase deliverOrderUseCase(CreateOrUpdateOrderUseCase createOrUpdateOrderUseCase,
			RetrieveOrderUseCaseImpl retrieveOrderUseCase) {
		return new DeliverOrderUseCaseImpl(createOrUpdateOrderUseCase, retrieveOrderUseCase);
	}

	@Bean
	public KitchenRetrieveUseCase kitchenRetrieveUseCase(KitchenDataProvider kitchenDataProvider) {
		return new KitchenRetrieveUseCaseImpl(kitchenDataProvider);
	}

	@Bean
	public KitchenCreateUseCase kitchenCreateUseCase(KitchenDataProvider kitchenDataProvider) {
		return new KitchenCreateUseCaseImpl(kitchenDataProvider);
	}

	@Bean
	public KitchenUpdateUseCase kitchenUpdateUseCase(KitchenDataProvider kitchenDataProvider) {
		return new KitchenUpdateUseCaseImpl(kitchenDataProvider);
	}

	@Bean
	public DeleteItemUseCase deleteItemUseCase(ItemDataProvider itemDataProvider) {
		return new DeleteItemUseCaseImpl(itemDataProvider);
	}

	@Bean
	public HttpMessageConverter<BufferedImage> bufferedImageHttpMessageConverter() {
		BufferedImageHttpMessageConverter converter = new BufferedImageHttpMessageConverter();
		converter.setDefaultContentType(MediaType.IMAGE_PNG);
		return converter;
	}

}
