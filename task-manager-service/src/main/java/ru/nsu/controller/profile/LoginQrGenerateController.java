package ru.nsu.controller.profile;

import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.userservice.profile.qr.LoginQrGenerateService;

import java.io.IOException;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static ru.nsu.common.constants.Path.PROFILE_GENERATE_QR_CODE_ENDPOINT;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginQrGenerateController {

    private final LoginQrGenerateService loginQrGenerateService;

    @GetMapping(
        value = PROFILE_GENERATE_QR_CODE_ENDPOINT,
        produces = IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] generateLoginQRCode(
        @RequestHeader("Authorization") String principal
    ) throws IOException, WriterException {
        log.info("generateLoginQRCode <- principal: {}", principal);

        return loginQrGenerateService.generateQrCode(principal);
    }

}
