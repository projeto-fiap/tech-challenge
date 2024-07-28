package tech.fiap.project.infra.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriTemplateHandler;
import tech.fiap.project.domain.dataprovider.ItemDataProvider;
import tech.fiap.project.domain.usecase.impl.item.CreateItemUseCaseImpl;
import tech.fiap.project.domain.usecase.impl.item.InitializeItemUseCaseImpl;
import tech.fiap.project.domain.usecase.impl.item.RetrieveItemUseCaseImpl;
import tech.fiap.project.domain.usecase.impl.item.CreateOrUpdateOrderUseCaseImpl;
import tech.fiap.project.domain.usecase.impl.order.RetrieveOrderUseCaseImpl;
import tech.fiap.project.domain.usecase.impl.person.*;
import tech.fiap.project.domain.usecase.item.InitializeItemUseCase;
import tech.fiap.project.domain.usecase.person.InitializePersonUseCase;
import tech.fiap.project.domain.dataprovider.OrderDataProvider;
import tech.fiap.project.domain.dataprovider.PersonDataProvider;
import tech.fiap.project.domain.usecase.impl.*;

import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@org.springframework.context.annotation.Configuration
@Getter
@Setter
public class Configuration {

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
		objectMapper.setDateFormat(df);
		return objectMapper;
	}

	@Bean
	public RestTemplate restTemplateMercadoPago() {
		UriTemplateHandler uriTemplateHandler = new DefaultUriBuilderFactory(MercadoPagoConstants.BASE_URI);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setUriTemplateHandler(uriTemplateHandler);
		return restTemplate;
	}

	@Bean
	public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
		return new BufferedImageHttpMessageConverter();
	}

	@Bean
	public GenerateQrCode generateQrCode() {
		return new GenerateQrCode();
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
	public CreateOrUpdateOrderUseCaseImpl createOrUpdateOrderUseCaseImpl(
			InitializeItemUseCase initializeItemUseCaseImpl, OrderDataProvider orderDataProvider,
			InitializePersonUseCase initializePersonUseCase) {
		return new CreateOrUpdateOrderUseCaseImpl(orderDataProvider, initializePersonUseCase, initializeItemUseCaseImpl);
	}

}
