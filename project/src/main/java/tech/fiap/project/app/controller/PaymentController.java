package tech.fiap.project.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.fiap.project.app.dto.OrderDTO;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.app.service.CreateQrCodeService;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {

    @Autowired
    private CreateQrCodeService createQrCode;
    @PostMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> createQrCode(@RequestBody OrderDTO orderDTO) {
        BufferedImage qrcode = createQrCode.execute(orderDTO);
        return ResponseEntity.ok(qrcode);
    }

}
