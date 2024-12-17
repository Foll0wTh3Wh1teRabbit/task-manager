package ru.nsu.taskservice.project.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nsu.common.dto.ProjectDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreateResponseDTO {

    private ProjectDTO project;

}
