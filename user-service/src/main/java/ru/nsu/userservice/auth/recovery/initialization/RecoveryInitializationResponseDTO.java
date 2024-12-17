package ru.nsu.userservice.auth.recovery.initialization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecoveryInitializationResponseDTO {

    private String email;

}
