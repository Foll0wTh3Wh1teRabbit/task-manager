package ru.nsu.common.mapper;

import ru.nsu.common.dto.RelationDTO;
import ru.nsu.common.dto.TaskDTO;
import ru.nsu.common.model.Task;

import java.util.Set;
import java.util.stream.Collectors;

public class TaskMapper {

    public static TaskDTO toTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setEstimatedTime(task.getEstimatedTime());
        taskDTO.setDeadlineTime(task.getDeadlineTime());
        taskDTO.setIsCompleted(task.getIsCompleted());

        Set<RelationDTO> relationsFrom = task.getRelationsFrom()
            .stream()
            .map(RelationMapper::toRelationDTO)
            .collect(Collectors.toSet());

        Set<RelationDTO> relationsTo = task.getRelationsTo()
            .stream()
            .map(RelationMapper::toRelationDTO)
            .collect(Collectors.toSet());

        taskDTO.setRelationsFrom(relationsFrom);
        taskDTO.setRelationsTo(relationsTo);

        return taskDTO;
    }

}
