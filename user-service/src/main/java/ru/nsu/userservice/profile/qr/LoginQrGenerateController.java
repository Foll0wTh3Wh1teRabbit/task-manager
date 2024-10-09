package ru.nsu.userservice.profile.qr;

import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.common.constants.Path;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginQrGenerateController {

    private final LoginQrGenerateService loginQrGenerateService;

    @GetMapping(
        value = Path.PROFILE + Path.GENERATE + Path.QR_CODE,
        produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] generateLoginQRCode(
        @RequestHeader("Authorization") String principal
    ) throws IOException, WriterException {
        log.info("generateLoginQRCode <- principal: {}", principal);

        return loginQrGenerateService.generateQrCode(principal);
    }

}
