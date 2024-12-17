package ru.nsu.taskservice.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.common.model.Task;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class RelationInconsistencyCheckerServiceImpl implements RelationInconsistencyCheckerService {

    @Override
    public boolean isTasksGraphConsistent(Set<Task> tasks) {
        return false;
    }

}
