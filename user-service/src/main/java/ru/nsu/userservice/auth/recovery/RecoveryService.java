package ru.nsu.userservice.auth.recovery;

public interface RecoveryService {

    RecoveryResponseDTO recovery(AbstractRecoveryDTO recoveryDTO);

}
