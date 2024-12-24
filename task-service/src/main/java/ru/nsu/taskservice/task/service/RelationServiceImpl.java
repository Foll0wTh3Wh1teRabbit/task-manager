package ru.nsu.taskservice.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.common.dto.TaskDTO;
import ru.nsu.common.dto.UnboundRelationDTO;
import ru.nsu.common.mapper.TaskMapper;
import ru.nsu.common.model.Project;
import ru.nsu.common.model.Task;
import ru.nsu.common.repository.TaskRepository;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class RelationServiceImpl implements RelationService {

    private final RelationModificationService relationModificationService;

    private final InconsistencyCheckerService inconsistencyCheckerService;

    private final TaskRepository taskRepository;

    @Override
    public TaskDTO modifyRelations(
        Long taskFromId,
        List<UnboundRelationDTO> relationsToRemove,
        List<UnboundRelationDTO> relationsToAdd
    ) {

        Task task = taskRepository.findById(taskFromId)
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        relationModificationService.removeRelations(task, relationsToRemove);
        relationModificationService.addRelations(task, relationsToAdd);

        Project project = task.getProject();
        Set<Task> tasksInSameProject = project.getTasks();

        boolean isTasksGraphInconsistent = inconsistencyCheckerService.isTasksGraphInconsistent(tasksInSameProject);
        if (isTasksGraphInconsistent) {
            throw new IllegalStateException("Tasks graph is not consistent");
        }

        return TaskMapper.toTaskDTO(task);
    }

}
