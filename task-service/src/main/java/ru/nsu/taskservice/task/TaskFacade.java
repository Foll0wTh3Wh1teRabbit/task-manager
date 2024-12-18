package ru.nsu.taskservice.task;

import ru.nsu.taskservice.task.create.TaskCreateRequestDTO;
import ru.nsu.taskservice.task.create.TaskCreateResponseDTO;
import ru.nsu.taskservice.task.update.TaskUpdateRequestDTO;
import ru.nsu.taskservice.task.update.TaskUpdateResponseDTO;

public interface TaskFacade {

    TaskCreateResponseDTO createTask(TaskCreateRequestDTO createRequestDTO);

    TaskUpdateResponseDTO updateTask(TaskUpdateRequestDTO updateRequestDTO);

    void deleteTask(Long taskId);

}
