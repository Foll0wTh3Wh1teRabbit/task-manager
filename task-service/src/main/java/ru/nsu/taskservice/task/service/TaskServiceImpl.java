package ru.nsu.taskservice.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.common.dto.TaskDTO;
import ru.nsu.common.mapper.TaskMapper;
import ru.nsu.common.model.Project;
import ru.nsu.common.model.Task;
import ru.nsu.common.repository.ProjectRepository;
import ru.nsu.common.repository.TaskRepository;
import ru.nsu.taskservice.task.create.TaskCreateRequestDTO;
import ru.nsu.taskservice.task.update.TaskUpdateRequestDTO;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final ProjectRepository projectRepository;

    private final TaskRepository taskRepository;

    @Override
    public TaskDTO createTask(TaskCreateRequestDTO taskCreateRequestDTO) {
        Project project = projectRepository.findById(taskCreateRequestDTO.getProjectId())
            .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        Task task = new Task();

        task.setName(taskCreateRequestDTO.getName());
        task.setPriority(taskCreateRequestDTO.getPriority());
        task.setEstimatedTime(taskCreateRequestDTO.getEstimatedTime());
        task.setDeadlineTime(taskCreateRequestDTO.getDeadlineTime());
        task.setIsCompleted(false);
        task.setProject(project);

        taskRepository.saveAndFlush(task);

        return TaskMapper.toTaskDTO(task);
    }

    @Override
    public TaskDTO updateTask(TaskUpdateRequestDTO taskUpdateRequestDTO) {
        Task task = taskRepository.findById(taskUpdateRequestDTO.getTaskId())
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        task.setName(taskUpdateRequestDTO.getName());
        task.setPriority(taskUpdateRequestDTO.getPriority());
        task.setEstimatedTime(taskUpdateRequestDTO.getEstimatedTime());
        task.setDeadlineTime(taskUpdateRequestDTO.getDeadlineTime());
        task.setIsCompleted(taskUpdateRequestDTO.getIsCompleted());

        taskRepository.saveAndFlush(task);

        return TaskMapper.toTaskDTO(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

}
