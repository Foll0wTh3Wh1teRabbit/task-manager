package ru.nsu.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static ru.nsu.common.constants.Path.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LogoutController {

    @PostMapping(value = AUTH_LOGOUT_ENDPOINT)
    public void logout() {
        log.info("logout <- ");
    }

}
