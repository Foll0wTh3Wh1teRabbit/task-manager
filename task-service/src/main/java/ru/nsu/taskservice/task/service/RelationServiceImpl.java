package ru.nsu.taskservice.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.common.dto.RelationDTO;
import ru.nsu.common.dto.UnboundRelationDTO;
import ru.nsu.common.model.Project;
import ru.nsu.common.model.Task;
import ru.nsu.common.repository.RelationRepository;
import ru.nsu.common.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class RelationServiceImpl implements RelationService {

    private final TaskRepository taskRepository;

    private final RelationRepository relationRepository;

    private final RelationInconsistencyCheckerService relationInconsistencyCheckerService;

    @Override
    public List<RelationDTO> modifyRelations(
        Long taskId,
        List<RelationDTO> relationsToRemove,
        List<UnboundRelationDTO> relationsToAdd
    ) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        removeRelations(task, relationsToRemove);
        addRelations(task, relationsToAdd);

        Project project = task.getProject();
        Set<Task> tasksInSameProject = project.getTasks();

        boolean isTasksGraphConsistent = relationInconsistencyCheckerService.isTasksGraphConsistent(tasksInSameProject);
        if (!isTasksGraphConsistent) {
            throw new IllegalStateException("Tasks graph is not consistent");
        }

        return new ArrayList<>();
    }

    private void removeRelations(Task task, List<RelationDTO> relationsToRemove) {

    }

    private void addRelations(Task task, List<UnboundRelationDTO> relationsToAdd) {

    }

}
