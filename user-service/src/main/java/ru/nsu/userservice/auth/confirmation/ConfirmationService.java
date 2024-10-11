package ru.nsu.userservice.auth.confirmation;

public interface ConfirmationService {

    ConfirmationResponseDTO confirmation(AbstractConfirmationDTO confirmationDTO);

}
