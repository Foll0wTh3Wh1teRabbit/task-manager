package ru.nsu.userservice.auth.confirmation.email;

public interface EmailConfirmationService {

    EmailConfirmationResponseDTO confirmation(EmailConfirmationRequestDTO confirmationDTO);

}
