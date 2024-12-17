package ru.nsu.controller.project;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.common.constants.Path;
import ru.nsu.taskservice.project.ProjectFacade;
import ru.nsu.taskservice.project.create.ProjectCreateRequestDTO;
import ru.nsu.taskservice.project.create.ProjectCreateResponseDTO;
import ru.nsu.taskservice.project.fetch.ProjectFetchResponseDTO;
import ru.nsu.taskservice.project.update.ProjectUpdateRequestDTO;
import ru.nsu.taskservice.project.update.ProjectUpdateResponseDTO;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectFacade projectFacade;

    @PutMapping(value = Path.PROJECT_CREATE_ENDPOINT)
    public ProjectCreateResponseDTO createProject(
        @RequestHeader("Authorization") String principal,
        @RequestBody @Valid ProjectCreateRequestDTO projectCreateRequestDTO
    ) {
        log.info("createProject <- dto: {}", projectCreateRequestDTO);

        return projectFacade.createProject(projectCreateRequestDTO, principal);
    }

    @GetMapping(value = Path.PROJECT_FETCH_ENDPOINT)
    public ProjectFetchResponseDTO fetchProjects(
        @RequestHeader("Authorization") String principal,
        @RequestParam(required = false) Long projectId
    ) {
        log.info("fetchProjects <- id: {}", projectId);

        return projectFacade.fetchProjects(projectId, principal);
    }

    @PatchMapping(value = Path.PROJECT_UPDATE_ENDPOINT)
    public ProjectUpdateResponseDTO updateProject(
        @RequestHeader("Authorization") String principal,
        @RequestBody @Valid ProjectUpdateRequestDTO projectUpdateRequestDTO
    ) {
        log.info("updateProject <- dto: {}", projectUpdateRequestDTO);

        return projectFacade.updateProject(projectUpdateRequestDTO, principal);
    }

    @DeleteMapping(value = Path.PROJECT_DELETE_ENDPOINT)
    public void deleteProject(
        @RequestHeader("Authorization") String principal,
        @RequestParam(required = false) Long projectId
    ) {
        log.info("deleteProject <- id: {}", projectId);

        projectFacade.deleteProject(projectId, principal);
    }

}
