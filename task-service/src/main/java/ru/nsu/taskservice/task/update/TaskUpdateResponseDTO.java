package ru.nsu.taskservice.task.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.nsu.common.dto.RelationDTO;
import ru.nsu.common.dto.TaskDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateResponseDTO {

    private TaskDTO task;

}
