package ru.nsu.userservice.profile.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nsu.common.constants.Parameters;
import ru.nsu.common.constants.Path;
import ru.nsu.common.constants.TokenTimeToLive;
import ru.nsu.common.model.User;
import ru.nsu.common.repository.UserRepository;
import ru.nsu.common.service.JwtService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginQrGenerateServiceImpl implements LoginQrGenerateService {

    private static final String PNG_FORMAT = "PNG";

    private static final Integer QR_CODE_WIDTH = 1024;

    private static final Integer QR_CODE_HEIGHT = 1024;

    private static final Integer FOREGROUND_COLOR = 0xFF000000;

    private static final Integer BACKGROUND_COLOR = 0xFFFFFFFF;

    private final JwtService jwtService;

    private final UserRepository userRepository;

    @Value("${application.base-url}")
    private String baseUrl;

    @Override
    public byte[] generateQrCode(String principal) throws WriterException, IOException {
        String username = jwtService.extractUsername(principal);
        User user = userRepository.findByEmail(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("User with email " + username + " is not found")
            );

        String shortTimeToLiveToken = jwtService.generateToken(user, TokenTimeToLive.SHORT_TIME_TO_LIVE);
        String pathToLoginViaQrCode = baseUrl
            + Path.AUTH + Path.LOGIN + Path.QR_CODE
            + Parameters.PRINCIPAL_URI + shortTimeToLiveToken;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
            pathToLoginViaQrCode,
            BarcodeFormat.QR_CODE,
            QR_CODE_WIDTH,
            QR_CODE_HEIGHT
        );

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(FOREGROUND_COLOR, BACKGROUND_COLOR);
        MatrixToImageWriter.writeToStream(bitMatrix, PNG_FORMAT, pngOutputStream, matrixToImageConfig);

        return pngOutputStream.toByteArray();
    }

}
