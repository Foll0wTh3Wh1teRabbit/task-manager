package ru.nsu.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.userservice.auth.register.inapp.InAppRegisterRequestDTO;
import ru.nsu.userservice.auth.register.inapp.InAppRegisterResponseDTO;
import ru.nsu.userservice.auth.register.inapp.InAppRegisterService;

import static ru.nsu.common.constants.Path.AUTH_IN_APP_REGISTER_ENDPOINT;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final InAppRegisterService inAppRegisterService;

    @PutMapping(value = AUTH_IN_APP_REGISTER_ENDPOINT)
    public InAppRegisterResponseDTO registerInApp(@RequestBody @Valid InAppRegisterRequestDTO inAppRegisterRequestDTO) {
        log.info("register <- type: IN_APP, dto: {}", inAppRegisterRequestDTO);

        return inAppRegisterService.register(inAppRegisterRequestDTO);
    }

}
