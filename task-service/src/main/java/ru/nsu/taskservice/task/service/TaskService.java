package ru.nsu.taskservice.task.service;

import ru.nsu.common.dto.TaskDTO;
import ru.nsu.taskservice.task.create.TaskCreateRequestDTO;
import ru.nsu.taskservice.task.update.TaskUpdateRequestDTO;

public interface TaskService {

    TaskDTO createTask(TaskCreateRequestDTO taskCreateRequestDTO, Long projectId);

    TaskDTO updateTask(TaskUpdateRequestDTO taskUpdateRequestDTO, Long taskId);

    void deleteTask(Long taskId);

}
