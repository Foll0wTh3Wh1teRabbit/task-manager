package ru.nsu.taskservice.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.common.dto.RelationDTO;
import ru.nsu.common.dto.UnboundRelationDTO;
import ru.nsu.common.model.Project;
import ru.nsu.common.model.Task;
import ru.nsu.common.repository.TaskRepository;

import java.util.List;
import java.util.Set;

// TODO refactor
@Slf4j
@Service
@RequiredArgsConstructor
public class RelationServiceImpl implements RelationService {

    private final TaskRepository taskRepository;

    private final RelationInconsistencyCheckerService relationInconsistencyCheckerService;

    @Override
    public void modifyRelations(
        Long taskId,
        List<RelationDTO> relationsToRemove,
        List<UnboundRelationDTO> relationsToAdd
    ) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        removeRelations(relationsToRemove);
        addRelations(task, relationsToAdd);

        Project project = task.getProject();
        Set<Task> tasksInSameProject = project.getTasks();

        boolean isTasksGraphConsistent = relationInconsistencyCheckerService.isTasksGraphConsistent(tasksInSameProject);
        if (!isTasksGraphConsistent) {
            throw new IllegalStateException("Tasks graph is not consistent");
        }

        taskRepository.saveAndFlush(task);
    }

    private void removeRelations(List<RelationDTO> relationsToRemove) {

    }

    private void addRelations(Task task, List<UnboundRelationDTO> relationsToAdd) {

    }

}
