package ru.nsu.controller.task;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.common.constants.Path;
import ru.nsu.taskservice.task.TaskFacade;
import ru.nsu.taskservice.task.create.TaskCreateRequestDTO;
import ru.nsu.taskservice.task.create.TaskCreateResponseDTO;
import ru.nsu.taskservice.task.delete.TaskDeleteRequestDTO;
import ru.nsu.taskservice.task.update.TaskUpdateRequestDTO;
import ru.nsu.taskservice.task.update.TaskUpdateResponseDTO;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskFacade taskFacade;

    @PutMapping(value = Path.TASK_CREATE_ENDPOINT)
    public TaskCreateResponseDTO createTask(@RequestBody @Valid TaskCreateRequestDTO createRequestDTO) {
        log.info("createTask <- dto: {}", createRequestDTO);

        return taskFacade.createTask(createRequestDTO);
    }

    @PatchMapping(value = Path.TASK_UPDATE_ENDPOINT)
    public TaskUpdateResponseDTO updateTask(@RequestBody @Valid TaskUpdateRequestDTO updateRequestDTO) {
        log.info("updateTask <- dto: {}", updateRequestDTO);

        return taskFacade.updateTask(updateRequestDTO);
    }

    @DeleteMapping(value = Path.TASK_DELETE_ENDPOINT)
    public void deleteTask(@RequestBody @Valid TaskDeleteRequestDTO deleteRequestDTO) {
        log.info("deleteTask <- dto: {}", deleteRequestDTO);

        taskFacade.deleteTask(deleteRequestDTO);
    }

}
