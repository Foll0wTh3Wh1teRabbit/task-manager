package ru.nsu.taskservice.project.create;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreateRequestDTO {

    @NotEmpty(
        message = "Имя проекта не может быть пустым"
    )
    @Length(
        min = 8,
        max = 64,
        message = "Имя проекта должно быть от 8 до 64 символов"
    )
    private String projectName;

}
