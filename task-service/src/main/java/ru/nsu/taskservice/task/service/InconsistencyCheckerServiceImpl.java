package ru.nsu.taskservice.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.common.model.Task;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class InconsistencyCheckerServiceImpl implements InconsistencyCheckerService {

    @Override
    public boolean isCompletionForbidden(Task task, Set<Task> tasks) {
        return false;
    }

    @Override
    public boolean isTasksGraphInconsistent(Set<Task> tasks) {
        return false;
    }

    private static class TaskGraph {

    }

}
