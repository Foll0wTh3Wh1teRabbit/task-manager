package ru.nsu.userservice.auth.login.inapp;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(makeFinal = true)
public class InAppLoginResponseDTO {

    private String principal;

}
