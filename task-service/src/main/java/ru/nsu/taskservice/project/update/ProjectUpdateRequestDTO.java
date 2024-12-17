package ru.nsu.taskservice.project.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUpdateRequestDTO {

    private Long projectId;

    private String projectName;

}
