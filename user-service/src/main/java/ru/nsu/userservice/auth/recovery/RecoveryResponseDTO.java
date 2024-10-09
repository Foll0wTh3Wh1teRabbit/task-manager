package ru.nsu.userservice.auth.recovery;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecoveryResponseDTO {

    private String email;

}
