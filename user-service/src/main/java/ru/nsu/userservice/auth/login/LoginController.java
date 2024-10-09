package ru.nsu.userservice.auth.login;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.common.constants.Path;
import ru.nsu.userservice.auth.login.inapp.InAppLoginDTO;
import ru.nsu.userservice.auth.login.qr.QrCodeLoginDTO;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService inAppLoginService;

    private final LoginService qrCodeLoginService;

    @PostMapping(Path.AUTH + Path.LOGIN + Path.IN_APP)
    public LoginResponseDTO loginInApp(@RequestBody @Valid InAppLoginDTO inAppLoginDTO) {
        log.info("login <- type: IN_APP, dto: {}", inAppLoginDTO);

        return inAppLoginService.login(inAppLoginDTO);
    }

    @PostMapping(Path.AUTH + Path.LOGIN + Path.QR_CODE)
    public LoginResponseDTO loginQRCode(@RequestParam String principal) {
        log.info("login <- type: QR_CODE, principal: {}", principal);

        return qrCodeLoginService.login(new QrCodeLoginDTO(principal));
    }

    //TODO oauth2.0?

}
