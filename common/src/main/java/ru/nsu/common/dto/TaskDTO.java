package ru.nsu.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nsu.common.enumerated.TaskPriority;

import java.time.ZonedDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long id;

    private String name;

    private TaskPriority priority;

    private Long estimatedTime;

    private ZonedDateTime deadlineTime;

    private Boolean isCompleted;

    private Set<RelationDTO> relations;

}
