package ru.nsu.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.common.constants.Path;
import ru.nsu.userservice.auth.logout.LogoutRequestDTO;
import ru.nsu.userservice.auth.logout.LogoutResponseDTO;

import static ru.nsu.common.constants.Path.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LogoutController {

    @PostMapping(value = AUTH + LOGOUT)
    public LogoutResponseDTO logout(@RequestBody @Valid LogoutRequestDTO logoutRequestDTO) {
        log.info("logout <- dto: {}", logoutRequestDTO);

        return new LogoutResponseDTO();
    }

}
