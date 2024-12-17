package ru.nsu.userservice.auth.recovery.confirmation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecoveryConfirmationResponseDTO {

    private String email;

}
