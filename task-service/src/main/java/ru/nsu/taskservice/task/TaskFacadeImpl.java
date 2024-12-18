package ru.nsu.taskservice.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.common.dto.RelationDTO;
import ru.nsu.common.dto.TaskDTO;
import ru.nsu.common.dto.UnboundRelationDTO;
import ru.nsu.taskservice.task.create.TaskCreateRequestDTO;
import ru.nsu.taskservice.task.create.TaskCreateResponseDTO;
import ru.nsu.taskservice.task.service.RelationService;
import ru.nsu.taskservice.task.service.TaskService;
import ru.nsu.taskservice.task.update.TaskUpdateRequestDTO;
import ru.nsu.taskservice.task.update.TaskUpdateResponseDTO;

import java.util.List;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class TaskFacadeImpl implements TaskFacade {

    private final TaskService taskService;

    private final RelationService relationService;

    @Override
    public TaskCreateResponseDTO createTask(TaskCreateRequestDTO taskCreateRequestDTO) {
        Long projectId = taskCreateRequestDTO.getProjectId();

        TaskDTO task = taskService.createTask(taskCreateRequestDTO, projectId);

        Long taskId = task.getId();
        List<UnboundRelationDTO> unboundRelations = taskCreateRequestDTO.getRelationsToAdd();

        relationService.modifyRelations(taskId, null, unboundRelations);

        return new TaskCreateResponseDTO(task);
    }

    @Override
    public TaskUpdateResponseDTO updateTask(TaskUpdateRequestDTO taskUpdateRequestDTO) {
        Long taskId = taskUpdateRequestDTO.getTaskId();
        List<RelationDTO> relationsToRemove = taskUpdateRequestDTO.getRelationsToRemove();
        List<UnboundRelationDTO> relationsToAdd = taskUpdateRequestDTO.getRelationsToAdd();

        TaskDTO task = taskService.updateTask(taskUpdateRequestDTO, taskId);

        relationService.modifyRelations(taskId, relationsToRemove, relationsToAdd);

        return new TaskUpdateResponseDTO(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskService.deleteTask(taskId);
    }

}
