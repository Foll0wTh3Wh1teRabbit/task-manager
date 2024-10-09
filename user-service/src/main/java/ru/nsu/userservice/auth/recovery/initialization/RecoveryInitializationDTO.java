package ru.nsu.userservice.auth.recovery.initialization;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nsu.userservice.auth.recovery.AbstractRecoveryDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class RecoveryInitializationDTO extends AbstractRecoveryDTO {

    @NotEmpty(
        message = "Email-адрес не может быть пустым"
    )
    @Pattern(
        regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
        message = "Email-адрес должен быть задан в валидном формате"
    )
    private String email;

}
