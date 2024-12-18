package ru.nsu.taskservice.project.service;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.nsu.common.dto.ProjectDTO;
import ru.nsu.common.mapper.ProjectMapper;
import ru.nsu.common.model.Project;
import ru.nsu.common.model.User;
import ru.nsu.common.repository.ProjectRepository;
import ru.nsu.taskservice.project.create.ProjectCreateRequestDTO;
import ru.nsu.taskservice.project.update.ProjectUpdateRequestDTO;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectDTO createProject(ProjectCreateRequestDTO projectCreateRequestDTO, User user) {
        Project project = new Project();

        project.setUser(user);
        project.setName(projectCreateRequestDTO.getProjectName());

        projectRepository.saveAndFlush(project);

        return ProjectMapper.toProjectDTO(project);
    }

    @Override
    public List<ProjectDTO> fetchProjects(Long projectId, User user) {
        Specification<Project> projectFilterSpecification =
            (root, query, criteriaBuilder) -> {
                Predicate matchingUserCondition = criteriaBuilder.equal(root.get("user"), user);
                Predicate matchingProjectIdCondition = Optional.ofNullable(projectId)
                    .map(id -> criteriaBuilder.equal(root.get("id"), id))
                    .orElse(criteriaBuilder.isTrue(criteriaBuilder.literal(true)));

                return criteriaBuilder.and(matchingUserCondition, matchingProjectIdCondition);
            };

        List<Project> projects = projectRepository.findAll(projectFilterSpecification);

        return projects.stream()
            .map(ProjectMapper::toProjectDTO)
            .toList();
    }

    @Override
    public ProjectDTO updateProject(ProjectUpdateRequestDTO projectUpdateRequestDTO, User user) {
        Long projectIdToChange = projectUpdateRequestDTO.getProjectId();
        String projectNewName = projectUpdateRequestDTO.getProjectName();

        Project project = projectRepository.findById(projectIdToChange)
            .orElseThrow(
                () -> new IllegalArgumentException("Project not found")
            );

        project.setName(projectNewName);

        projectRepository.saveAndFlush(project);

        return ProjectMapper.toProjectDTO(project);
    }

    @Override
    public void deleteProject(Long projectId, User user) {
        projectRepository.deleteById(projectId);
    }

}
