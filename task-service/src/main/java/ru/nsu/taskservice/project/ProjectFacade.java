package ru.nsu.taskservice.project;

import ru.nsu.taskservice.project.create.ProjectCreateRequestDTO;
import ru.nsu.taskservice.project.create.ProjectCreateResponseDTO;
import ru.nsu.taskservice.project.fetch.ProjectFetchResponseDTO;
import ru.nsu.taskservice.project.update.ProjectUpdateRequestDTO;
import ru.nsu.taskservice.project.update.ProjectUpdateResponseDTO;

public interface ProjectFacade {

    ProjectCreateResponseDTO createProject(ProjectCreateRequestDTO projectCreateRequestDTO, String principal);

    ProjectFetchResponseDTO fetchProjects(Long projectId, String principal);

    ProjectUpdateResponseDTO updateProject(ProjectUpdateRequestDTO projectUpdateRequestDTO, String principal);

    void deleteProject(Long projectId, String principal);

}
