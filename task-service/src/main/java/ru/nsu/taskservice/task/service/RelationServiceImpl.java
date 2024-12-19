package ru.nsu.taskservice.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.common.dto.RelationDTO;
import ru.nsu.common.dto.TaskDTO;
import ru.nsu.common.dto.UnboundRelationDTO;
import ru.nsu.common.mapper.RelationMapper;
import ru.nsu.common.mapper.TaskMapper;
import ru.nsu.common.mapper.UnboundRelationMapper;
import ru.nsu.common.model.Project;
import ru.nsu.common.model.Task;
import ru.nsu.common.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

// TODO refactor and fix
@Slf4j
@Service
@RequiredArgsConstructor
public class RelationServiceImpl implements RelationService {

    private final TaskRepository taskRepository;

    private final RelationInconsistencyCheckerService relationInconsistencyCheckerService;

    @Override
    public TaskDTO modifyRelations(Long taskId, List<RelationDTO> relationsToRemove, List<UnboundRelationDTO> relationsToAdd) {
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

        taskRepository.saveAndFlush(task);

        return TaskMapper.toTaskDTO(task);
    }

    private void removeRelations(Task task, List<RelationDTO> relationsToRemove) {
        Optional.ofNullable(relationsToRemove)
            .map(
                relations -> relations.stream()
                    .map(
                        relation -> {
                            Task taskTo = taskRepository.findById(relation.getTaskToId())
                                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

                            return UnboundRelationMapper.toRelation(
                                task,
                                taskTo,
                                RelationMapper.toUnboundRelationDTO(relation)
                            );
                        }
                    )
                    .collect(Collectors.toSet())
            )
            .ifPresent(set -> task.getRelationsFrom().removeAll(set));
    }

    private void addRelations(Task task, List<UnboundRelationDTO> relationsToAdd) {
        Optional.ofNullable(relationsToAdd)
            .map(
                relations -> relations.stream()
                    .map(
                        unboundRelation -> {
                            Task taskTo = taskRepository.findById(unboundRelation.getTaskToId())
                                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

                            return UnboundRelationMapper.toRelation(task, taskTo, unboundRelation);
                        }
                    )
                    .collect(Collectors.toSet())
            )
            .ifPresent(set -> task.getRelationsFrom().addAll(set));
    }

}
