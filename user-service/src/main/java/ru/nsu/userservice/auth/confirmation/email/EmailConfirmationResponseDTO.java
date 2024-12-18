package ru.nsu.userservice.auth.confirmation.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailConfirmationResponseDTO {

    private String principal;

}
