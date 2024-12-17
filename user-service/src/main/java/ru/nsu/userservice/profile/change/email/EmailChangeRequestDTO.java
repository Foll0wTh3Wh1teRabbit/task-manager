package ru.nsu.userservice.profile.change.email;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailChangeRequestDTO {

    @NotEmpty(
        message = "Email-адрес не может быть пустым"
    )
    @Pattern(
        regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
        message = "Email-адрес должен быть задан в валидном формате"
    )
    private String newEmail;

}
