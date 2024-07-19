package tech.fiap.project.domain.usecase.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;

public class GenerateQrCode {
    public BufferedImage execute(String barcodeText) {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        try {
            return MatrixToImageWriter.toBufferedImage(barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200));
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }
}
