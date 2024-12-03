package ru.nsu.userservice.auth.recovery.initialization;

public interface RecoveryInitializationService {

    RecoveryInitializationResponseDTO recovery(RecoveryInitializationRequestDTO recoveryDTO);

}
