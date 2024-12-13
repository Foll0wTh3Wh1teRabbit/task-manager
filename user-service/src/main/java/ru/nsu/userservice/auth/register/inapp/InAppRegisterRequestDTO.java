package ru.nsu.userservice.auth.register.inapp;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@FieldDefaults(makeFinal = true)
public class InAppRegisterRequestDTO {

    @NotEmpty(
        message = "Email-адрес не может быть пустым"
    )
    @Pattern(
        regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
        message = "Email-адрес должен быть задан в валидном формате"
    )
    private String email;

    @NotEmpty(
        message = "Пароль не может быть пустым"
    )
    @Length(
        min = 8,
        max = 64,
        message = "Пароль должен быть от 8 до 64 символов"
    )
    @ToString.Exclude
    private String password;

}
