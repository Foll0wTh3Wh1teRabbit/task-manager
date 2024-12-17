package ru.nsu.taskservice.project.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nsu.common.dto.ProjectDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUpdateResponseDTO {

    private ProjectDTO project;

}
