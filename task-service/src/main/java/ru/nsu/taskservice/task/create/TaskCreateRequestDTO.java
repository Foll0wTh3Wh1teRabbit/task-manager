package ru.nsu.taskservice.task.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nsu.common.dto.UnboundRelationDTO;
import ru.nsu.common.enumerated.TaskPriority;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreateRequestDTO {

    private Long projectId;

    private String name;

    private TaskPriority priority;

    private Long estimatedTime;

    private ZonedDateTime deadlineTime;

    private List<UnboundRelationDTO> relationsToAdd;

}
