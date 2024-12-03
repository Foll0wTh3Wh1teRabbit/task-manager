package ru.nsu.userservice.auth.register.inapp;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(makeFinal = true)
public class InAppRegisterResponseDTO {

    private String email;

}
