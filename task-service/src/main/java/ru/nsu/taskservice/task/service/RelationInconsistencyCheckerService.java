package ru.nsu.taskservice.task.service;

import ru.nsu.common.model.Task;

import java.util.Set;

public interface RelationInconsistencyCheckerService {

    boolean isTasksGraphConsistent(Set<Task> tasks);

}
