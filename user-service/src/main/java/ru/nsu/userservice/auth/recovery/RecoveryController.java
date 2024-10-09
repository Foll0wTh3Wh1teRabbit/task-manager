package ru.nsu.userservice.auth.recovery;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.common.constants.Path;
import ru.nsu.userservice.auth.recovery.confirmation.RecoveryConfirmationDTO;
import ru.nsu.userservice.auth.recovery.initialization.RecoveryInitializationDTO;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class RecoveryController {

    private final RecoveryService recoveryInitializationService;

    private final RecoveryService recoveryConfirmationService;

    @PostMapping(Path.AUTH + Path.RECOVERY)
    public RecoveryResponseDTO recoveryInitialization(
        @RequestBody @Valid RecoveryInitializationDTO recoveryInitializationDTO
    ) {
        log.info("recovery <- step: INITIALIZATION, dto: {}", recoveryInitializationDTO);

        return recoveryInitializationService.recovery(recoveryInitializationDTO);
    }

    @PatchMapping(Path.AUTH + Path.RECOVERY)
    public RecoveryResponseDTO recoveryConfirmation(
        @RequestParam String principal,
        @RequestBody RecoveryConfirmationDTO recoveryConfirmationDTO
    ) {
        log.info("recovery <- type: CONFIRMATION");

        recoveryConfirmationDTO.setPrincipal(principal);

        return recoveryConfirmationService.recovery(recoveryConfirmationDTO);
    }

}
