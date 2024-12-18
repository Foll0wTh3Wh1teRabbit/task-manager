package ru.nsu.taskservice.task.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nsu.common.dto.TaskDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateResponseDTO {

    private TaskDTO task;

}
