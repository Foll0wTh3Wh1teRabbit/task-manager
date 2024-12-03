package ru.nsu.userservice.auth.logout;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(makeFinal = true)
public class LogoutRequestDTO {

    private String principal;

}
