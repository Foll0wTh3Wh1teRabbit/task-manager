package ru.nsu.taskservice.project.service;

import ru.nsu.common.dto.ProjectDTO;
import ru.nsu.common.model.User;
import ru.nsu.taskservice.project.create.ProjectCreateRequestDTO;
import ru.nsu.taskservice.project.update.ProjectUpdateRequestDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO createProject(ProjectCreateRequestDTO projectCreateRequestDTO, User user);

    List<ProjectDTO> fetchProjects(Long projectId, User user);

    ProjectDTO updateProject(ProjectUpdateRequestDTO projectUpdateRequestDTO, User user);

    void deleteProject(Long projectId, User user);

}
