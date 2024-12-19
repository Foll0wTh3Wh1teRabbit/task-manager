package ru.nsu.taskservice.task.service;

import ru.nsu.common.dto.TaskDTO;
import ru.nsu.taskservice.task.create.TaskCreateRequestDTO;
import ru.nsu.taskservice.task.update.TaskUpdateRequestDTO;

public interface TaskService {

    TaskDTO createTask(TaskCreateRequestDTO taskCreateRequestDTO);

    TaskDTO updateTask(TaskUpdateRequestDTO taskUpdateRequestDTO);

    void deleteTask(Long taskId);

}
