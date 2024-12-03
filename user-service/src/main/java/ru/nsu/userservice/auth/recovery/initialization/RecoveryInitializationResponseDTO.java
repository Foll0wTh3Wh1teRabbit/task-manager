package ru.nsu.userservice.auth.recovery.initialization;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(makeFinal = true)
public class RecoveryInitializationResponseDTO {

    private String email;

}
