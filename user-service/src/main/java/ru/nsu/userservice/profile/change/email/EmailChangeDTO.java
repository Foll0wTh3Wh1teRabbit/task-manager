package ru.nsu.userservice.profile.change.email;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nsu.userservice.profile.change.AbstractChangeDTO;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmailChangeDTO extends AbstractChangeDTO {

    @NotEmpty(
        message = "Email-адрес не может быть пустым"
    )
    @Pattern(
        regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
        message = "Email-адрес должен быть задан в валидном формате"
    )
    private String newEmail;

}
