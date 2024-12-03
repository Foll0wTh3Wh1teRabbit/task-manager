package ru.nsu.userservice.auth.confirmation.email;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(makeFinal = true)
public class EmailConfirmationRequestDTO {

    @NotEmpty(
        message = "Закодированный токен не может быть пустым"
    )
    @Pattern(
        regexp = "(^[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*$)",
        message = "Токен должен быть валидного формата"
    )
    private String principal;

}
