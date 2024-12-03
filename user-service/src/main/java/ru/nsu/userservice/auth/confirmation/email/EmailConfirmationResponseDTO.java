package ru.nsu.userservice.auth.confirmation.email;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(makeFinal = true)
public class EmailConfirmationResponseDTO {

    private String principal;

}
