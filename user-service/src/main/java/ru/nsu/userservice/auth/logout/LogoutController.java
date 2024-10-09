package ru.nsu.userservice.auth.logout;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.common.constants.Path;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LogoutController {

    @PostMapping(Path.AUTH + Path.LOGOUT)
    public LogoutResponseDTO logout(@RequestBody @Valid LogoutDTO logoutDTO) {
        log.info("logout <- dto: {}", logoutDTO);

        return new LogoutResponseDTO();
    }

}
