package ru.nsu.userservice.auth.recovery.confirmation;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(makeFinal = true)
public class RecoveryConfirmationResponseDTO {

    private String email;

}
