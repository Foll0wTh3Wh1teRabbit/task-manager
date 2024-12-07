package ru.nsu.userservice.auth.confirmation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.common.constants.Path;
import ru.nsu.userservice.auth.confirmation.email.EmailConfirmationRequestDTO;
import ru.nsu.userservice.auth.confirmation.email.EmailConfirmationResponseDTO;
import ru.nsu.userservice.auth.confirmation.email.EmailConfirmationService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ConfirmationController {

    private final EmailConfirmationService emailConfirmationService;

    @PatchMapping(Path.AUTH + Path.CONFIRMATION)
    public EmailConfirmationResponseDTO emailConfirmation(@RequestParam String principal) {
        log.info("confirm <- type: EMAIL, principal: {}", principal);

        return emailConfirmationService.confirmation(
            new EmailConfirmationRequestDTO(principal)
        );
    }

}
