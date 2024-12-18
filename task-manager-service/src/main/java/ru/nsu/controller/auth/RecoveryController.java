package ru.nsu.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.userservice.auth.recovery.confirmation.RecoveryConfirmationRequestDTO;
import ru.nsu.userservice.auth.recovery.confirmation.RecoveryConfirmationResponseDTO;
import ru.nsu.userservice.auth.recovery.confirmation.RecoveryConfirmationService;
import ru.nsu.userservice.auth.recovery.initialization.RecoveryInitializationRequestDTO;
import ru.nsu.userservice.auth.recovery.initialization.RecoveryInitializationResponseDTO;
import ru.nsu.userservice.auth.recovery.initialization.RecoveryInitializationService;

import static ru.nsu.common.constants.Path.AUTH_RECOVERY_ENDPOINT;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class RecoveryController {

    private final RecoveryInitializationService recoveryInitializationService;

    private final RecoveryConfirmationService recoveryConfirmationService;

    @PostMapping(value = AUTH_RECOVERY_ENDPOINT)
    public RecoveryInitializationResponseDTO recoveryInitialization(
        @RequestBody @Valid RecoveryInitializationRequestDTO recoveryInitializationRequestDTO
    ) {
        log.info("recovery <- step: INITIALIZATION, dto: {}", recoveryInitializationRequestDTO);

        return recoveryInitializationService.recovery(recoveryInitializationRequestDTO);
    }

    @PatchMapping(value = AUTH_RECOVERY_ENDPOINT)
    public RecoveryConfirmationResponseDTO recoveryConfirmation(
        @RequestParam String principal,
        @RequestBody RecoveryConfirmationRequestDTO recoveryConfirmationRequestDTO
    ) {
        log.info("recovery <- type: CONFIRMATION");

        recoveryConfirmationRequestDTO.setPrincipal(principal);

        return recoveryConfirmationService.recovery(recoveryConfirmationRequestDTO);
    }

}
