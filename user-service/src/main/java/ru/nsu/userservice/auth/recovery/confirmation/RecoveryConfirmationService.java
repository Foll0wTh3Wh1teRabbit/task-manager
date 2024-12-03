package ru.nsu.userservice.auth.recovery.confirmation;

public interface RecoveryConfirmationService {

    RecoveryConfirmationResponseDTO recovery(RecoveryConfirmationRequestDTO recoveryDTO);

}
