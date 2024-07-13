package tech.fiap.project.infra.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriTemplateHandler;
import tech.fiap.project.domain.usecase.GenerateQrCode;

import java.awt.image.BufferedImage;

@org.springframework.context.annotation.Configuration
@Getter
@Setter
public class Configuration {
    @Bean
    public RestTemplate restTemplateMercadoPago(){
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
    GenerateQrCode generateQrCode() {
        return new GenerateQrCode();
    }
}
