package ru.nsu.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.common.constants.Path;
import ru.nsu.userservice.auth.login.inapp.InAppLoginRequestDTO;
import ru.nsu.userservice.auth.login.inapp.InAppLoginResponseDTO;
import ru.nsu.userservice.auth.login.inapp.InAppLoginService;
import ru.nsu.userservice.auth.login.qr.QrCodeLoginRequestDTO;
import ru.nsu.userservice.auth.login.qr.QrCodeLoginResponseDTO;
import ru.nsu.userservice.auth.login.qr.QrCodeLoginService;

import static ru.nsu.common.constants.Path.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final InAppLoginService inAppLoginService;

    private final QrCodeLoginService qrCodeLoginService;

    @PostMapping(value = AUTH + LOGIN + IN_APP)
    public InAppLoginResponseDTO loginInApp(@RequestBody @Valid InAppLoginRequestDTO inAppLoginRequestDTO) {
        log.info("login <- type: IN_APP, dto: {}", inAppLoginRequestDTO);

        return inAppLoginService.login(inAppLoginRequestDTO);
    }

    @PostMapping(value = AUTH + LOGIN + QR_CODE)
    public QrCodeLoginResponseDTO loginQRCode(@RequestParam String principal) {
        log.info("login <- type: QR_CODE, principal: {}", principal);

        return qrCodeLoginService.login(new QrCodeLoginRequestDTO(principal));
    }

}
