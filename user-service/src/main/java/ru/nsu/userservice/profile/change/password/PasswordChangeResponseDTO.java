package ru.nsu.userservice.profile.change.password;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(makeFinal = true)
public class PasswordChangeResponseDTO {

    private String principal;

}
