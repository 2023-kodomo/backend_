package com.example.kodomoproject.domain.qr.service;

import com.example.kodomoproject.domain.qr.exception.QrGenerationFailedException;
import com.example.kodomoproject.domain.qr.request.QrRequest;
import com.example.kodomoproject.global.error.exception.InvalidDataException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class QrGenerateService {

    public BufferedImage execute(QrRequest request) throws WriterException {

        if (request.getUrl().isBlank()) {
            throw InvalidDataException.EXCEPTION;
        }
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(
                    request.getUrl(),
                    BarcodeFormat.QR_CODE,
                    300, 300
            );

            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            throw QrGenerationFailedException.EXCEPTION;
        }
    }

}
