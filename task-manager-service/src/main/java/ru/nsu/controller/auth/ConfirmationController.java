package ru.nsu.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.userservice.auth.confirmation.email.EmailConfirmationRequestDTO;
import ru.nsu.userservice.auth.confirmation.email.EmailConfirmationResponseDTO;
import ru.nsu.userservice.auth.confirmation.email.EmailConfirmationService;

import static ru.nsu.common.constants.Path.AUTH_CONFIRMATION_ENDPOINT;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ConfirmationController {

    private final EmailConfirmationService emailConfirmationService;

    @GetMapping(value = AUTH_CONFIRMATION_ENDPOINT)
    public EmailConfirmationResponseDTO emailConfirmation(@RequestParam String principal) {
        log.info("confirm <- type: EMAIL, principal: {}", principal);

        return emailConfirmationService.confirmation(
            new EmailConfirmationRequestDTO(principal)
        );
    }

}
