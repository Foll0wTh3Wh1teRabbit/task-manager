package ru.nsu.userservice.auth.recovery.confirmation;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Data
public class RecoveryConfirmationRequestDTO {

    @Nullable
    private String principal;

    @NotEmpty(
        message = "Пароль не может быть пустым"
    )
    @Length(
        min = 8,
        max = 64,
        message = "Пароль должен быть от 8 до 64 символов"
    )
    @ToString.Exclude
    private String newPassword;

}
