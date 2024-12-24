package ru.nsu.taskservice.task.service;

import ru.nsu.common.model.Task;

import java.util.Set;

public interface InconsistencyCheckerService {

    boolean isCompletionForbidden(Task task, Set<Task> tasks);

    boolean isTasksGraphInconsistent(Set<Task> tasks);

}
