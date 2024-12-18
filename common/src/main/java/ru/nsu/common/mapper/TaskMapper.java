package ru.nsu.common.mapper;

import ru.nsu.common.dto.RelationDTO;
import ru.nsu.common.dto.TaskDTO;
import ru.nsu.common.model.Task;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskMapper {

    public static TaskDTO toTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setEstimatedTime(task.getEstimatedTime());
        taskDTO.setDeadlineTime(task.getDeadlineTime());
        taskDTO.setIsCompleted(task.getIsCompleted());

        Set<RelationDTO> relations = Stream.of(task.getRelationsFrom(), task.getRelationsTo())
            .flatMap(Set::stream)
            .map(RelationMapper::toRelationDTO)
            .collect(Collectors.toSet());

        taskDTO.setRelations(relations);

        return taskDTO;
    }

}
