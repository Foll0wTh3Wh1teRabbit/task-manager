package ru.nsu.userservice.auth.recovery.confirmation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecoveryConfirmationResponseDTO {

    private String email;

}
