package ru.nsu.userservice.auth.login.qr;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(makeFinal = true)
public class QrCodeLoginRequestDTO {

    @NotEmpty(
        message = "Закодированный токен не может быть пустым"
    )
    @Pattern(
        regexp = "(^[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*$)",
        message = "Токен должен быть валидного формата"
    )
    private String principal;

}
