package ru.nsu.common.mapper;

import ru.nsu.common.dto.ProjectDTO;
import ru.nsu.common.model.Project;

public class ProjectMapper {

    public static ProjectDTO toProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setTasks(project.getTasksOfProject());

        return projectDTO;
    }

}
