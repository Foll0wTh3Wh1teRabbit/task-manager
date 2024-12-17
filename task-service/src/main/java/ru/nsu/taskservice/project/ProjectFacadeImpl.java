package ru.nsu.taskservice.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.common.dto.ProjectDTO;
import ru.nsu.common.model.User;
import ru.nsu.common.service.CustomUserDetailsService;
import ru.nsu.common.service.JwtService;
import ru.nsu.taskservice.project.create.ProjectCreateRequestDTO;
import ru.nsu.taskservice.project.create.ProjectCreateResponseDTO;
import ru.nsu.taskservice.project.fetch.ProjectFetchResponseDTO;
import ru.nsu.taskservice.project.service.ProjectService;
import ru.nsu.taskservice.project.update.ProjectUpdateRequestDTO;
import ru.nsu.taskservice.project.update.ProjectUpdateResponseDTO;

import java.util.List;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class ProjectFacadeImpl implements ProjectFacade {

    private final ProjectService projectService;

    private final JwtService jwtService;

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public ProjectCreateResponseDTO createProject(ProjectCreateRequestDTO projectCreateRequestDTO, String principal) {
        String username = jwtService.extractUsername(principal);
        User user = customUserDetailsService.loadUserByUsername(username);

        ProjectDTO project = projectService.createProject(projectCreateRequestDTO, user);

        return new ProjectCreateResponseDTO(project);
    }

    @Override
    public ProjectFetchResponseDTO fetchProjects(Long projectId, String principal) {
        String username = jwtService.extractUsername(principal);
        User user = customUserDetailsService.loadUserByUsername(username);

        List<ProjectDTO> foundProjects = projectService.fetchProjects(projectId, user);

        return new ProjectFetchResponseDTO(foundProjects);
    }

    @Override
    public ProjectUpdateResponseDTO updateProject(ProjectUpdateRequestDTO projectUpdateRequestDTO, String principal) {
        String username = jwtService.extractUsername(principal);
        User user = customUserDetailsService.loadUserByUsername(username);

        ProjectDTO project = projectService.updateProject(projectUpdateRequestDTO, user);

        return new ProjectUpdateResponseDTO(project);
    }

    @Override
    public void deleteProject(Long projectId, String principal) {
        String username = jwtService.extractUsername(principal);
        User user = customUserDetailsService.loadUserByUsername(username);

        projectService.deleteProject(projectId, user);
    }

}
