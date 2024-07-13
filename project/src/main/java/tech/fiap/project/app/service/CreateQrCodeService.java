package tech.fiap.project.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.OrderMapper;
import tech.fiap.project.app.dto.OrderDTO;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.usecase.CreatePaymentUrl;
import tech.fiap.project.domain.usecase.GenerateQrCode;

import java.awt.image.BufferedImage;

@Service
@AllArgsConstructor
public class CreateQrCodeService {

    private CreatePaymentUrl createPaymentUrl;

    private OrderMapper orderMapper;

    private GenerateQrCode generateQrCode;

    public BufferedImage execute(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        String paymentUrl = createPaymentUrl.execute(order);
        return generateQrCode.execute(paymentUrl);
    }
}
