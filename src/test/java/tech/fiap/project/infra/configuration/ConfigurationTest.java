package tech.fiap.project.infra.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import tech.fiap.project.domain.dataprovider.PersonDataProvider;
import tech.fiap.project.domain.usecase.impl.person.*;

import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class ConfigurationTest {

	@Autowired
	private Configuration configuration;

	@Test
	void testObjectMapper() {
		ObjectMapper objectMapper = configuration.objectMapper();

		assertNotNull(objectMapper);
		assertTrue(objectMapper.getRegisteredModuleIds().contains(new JavaTimeModule().getTypeId()));
		assertFalse(objectMapper.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));

		DateFormat df = objectMapper.getDateFormat();
		assertTrue(df instanceof SimpleDateFormat);
		assertEquals("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS", ((SimpleDateFormat) df).toPattern());
	}

	@Test
	void testRestTemplatePayments() {
		RestTemplate restTemplate = configuration.restTemplatePayments();

		assertNotNull(restTemplate);
		assertTrue(restTemplate.getUriTemplateHandler() instanceof DefaultUriBuilderFactory);
	}

	@Test
	void testRestTemplateKeycloak() {
		RestTemplate restTemplate = configuration.restTemplateKeycloak();

		assertNotNull(restTemplate);
	}

	@Test
	void testBufferedImageHttpMessageConverter() {
		HttpMessageConverter<BufferedImage> converter = configuration.bufferedImageHttpMessageConverter();

		assertNotNull(converter);
		assertTrue(converter instanceof BufferedImageHttpMessageConverter);
		assertEquals(MediaType.IMAGE_PNG, ((BufferedImageHttpMessageConverter) converter).getDefaultContentType());
	}

	@Test
	void testInitializePersonUseCase() {
		PersonDataProvider personDataProvider = mock(PersonDataProvider.class);
		InitializePersonUseCaseImpl useCase = configuration.initializePersonUseCase(personDataProvider);

		assertNotNull(useCase);
	}

	@Test
	void testRetrievePersonUseCase() {
		PersonDataProvider personDataProvider = mock(PersonDataProvider.class);
		RetrievePersonUseCaseImpl useCase = configuration.retrievePersonUseCase(personDataProvider);

		assertNotNull(useCase);
	}

	@Test
	void testUpdatePersonUseCase() {
		PersonDataProvider personDataProvider = mock(PersonDataProvider.class);
		UpdatePersonUseCaseImpl useCase = configuration.updatePersonUseCase(personDataProvider);

		assertNotNull(useCase);
	}

	@Test
	void testSavePersonUseCase() {
		PersonDataProvider personDataProvider = mock(PersonDataProvider.class);
		SavePersonUseCaseImpl useCase = configuration.savePersonUseCase(personDataProvider);

		assertNotNull(useCase);
	}

	@Test
	void testDeletePersonUseCase() {
		PersonDataProvider personDataProvider = mock(PersonDataProvider.class);
		DeletePersonUseCaseImpl useCase = configuration.deletePersonUseCase(personDataProvider);

		assertNotNull(useCase);
	}

}