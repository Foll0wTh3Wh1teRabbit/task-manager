package ru.nsu.taskservice.project.fetch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nsu.common.dto.ProjectDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectFetchResponseDTO {

    private List<ProjectDTO> projects;

}
