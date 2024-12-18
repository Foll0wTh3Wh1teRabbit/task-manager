package ru.nsu.taskservice.task.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nsu.common.dto.TaskDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreateResponseDTO {

    private TaskDTO task;

}
