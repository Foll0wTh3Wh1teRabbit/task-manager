package ru.nsu.taskservice.task.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDeleteRequestDTO {

    private Long taskId;

}
