package ru.nsu.userservice.auth.register;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.common.constants.Path;
import ru.nsu.userservice.auth.register.inapp.InAppRegisterDTO;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService inAppRegisterService;

    @PutMapping(Path.AUTH + Path.REGISTER + Path.IN_APP)
    public RegisterResponseDTO registerInApp(@RequestBody @Valid InAppRegisterDTO inAppRegisterDTO) {
        log.info("register <- type: IN_APP, dto: {}", inAppRegisterDTO);

        return inAppRegisterService.register(inAppRegisterDTO);
    }

}
