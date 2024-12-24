package ru.nsu.taskservice.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.common.dto.TaskDTO;
import ru.nsu.common.mapper.TaskMapper;
import ru.nsu.common.model.Project;
import ru.nsu.common.model.Task;
import ru.nsu.common.repository.ProjectRepository;
import ru.nsu.common.repository.TaskRepository;
import ru.nsu.taskservice.task.create.TaskCreateRequestDTO;
import ru.nsu.taskservice.task.update.TaskUpdateRequestDTO;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final InconsistencyCheckerService inconsistencyCheckerService;

    private final ProjectRepository projectRepository;

    private final TaskRepository taskRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TaskDTO updateTask(TaskUpdateRequestDTO taskUpdateRequestDTO) {
        Task task = taskRepository.findById(taskUpdateRequestDTO.getTaskId())
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        task.setName(taskUpdateRequestDTO.getName());
        task.setPriority(taskUpdateRequestDTO.getPriority());
        task.setEstimatedTime(taskUpdateRequestDTO.getEstimatedTime());
        task.setDeadlineTime(taskUpdateRequestDTO.getDeadlineTime());

        if (taskUpdateRequestDTO.getIsCompleted()) {
            Set<Task> tasksOfProject = task.getProject().getTasks();

            if (inconsistencyCheckerService.isCompletionForbidden(task, tasksOfProject)) {
                throw new IllegalStateException("Task can not be completed");
            }

            task.setIsCompleted(true);
        }

        taskRepository.saveAndFlush(task);

        return TaskMapper.toTaskDTO(task);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

}
