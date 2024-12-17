package ru.nsu.userservice.profile.change.password;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeRequestDTO {

    @NotEmpty(
        message = "Пароль не может быть пустым"
    )
    @Length(
        min = 8,
        max = 64,
        message = "Пароль должен быть от 8 до 64 символов"
    )
    @ToString.Exclude
    private String oldPassword;

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
