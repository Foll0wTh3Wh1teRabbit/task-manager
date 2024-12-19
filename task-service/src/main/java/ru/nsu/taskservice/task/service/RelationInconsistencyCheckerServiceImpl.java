package ru.nsu.taskservice.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.common.model.Task;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class RelationInconsistencyCheckerServiceImpl implements RelationInconsistencyCheckerService {

    @Override
    public boolean isTasksGraphConsistent(Set<Task> tasks) {
        return true;
    }

    private static class TaskGraph {

        private final boolean[][] graphMatrix;

        private TaskGraph(Set<Task> tasks) {
            List<Task> tasksWithoutSubtasks = reduceSubtaskTrees(tasks);

            graphMatrix = new boolean[tasksWithoutSubtasks.size()][tasksWithoutSubtasks.size()];

            for (int i = 0; i < tasksWithoutSubtasks.size(); i++) {
                for (int j = 0; j < tasksWithoutSubtasks.size(); j++) {
                    graphMatrix[i][j] = havePrecedingRelation(
                        tasksWithoutSubtasks.get(i),
                        tasksWithoutSubtasks.get(j)
                    );
                }
            }
        }

        private boolean isInconsistent() {
            int n = graphMatrix.length;
            boolean[] visited = new boolean[n];
            boolean[] inStack = new boolean[n];

            for (int i = 0; i < n; i++) {
                if (!visited[i] && dfs(graphMatrix, i, visited, inStack)) {
                    return true;
                }
            }

            return false;
        }

        private boolean dfs(boolean[][] graph, int node, boolean[] visited, boolean[] inStack) {
            visited[node] = true;
            inStack[node] = true;

            for (int neighbor = 0; neighbor < graph.length; neighbor++) {
                if (graph[node][neighbor]) {
                    if (!visited[neighbor]) {
                        if (dfs(graph, neighbor, visited, inStack)) {
                            return true;
                        }
                    } else if (inStack[neighbor]) {
                        return true;
                    }
                }
            }

            inStack[node] = false;

            return false;
        }

        private List<Task> reduceSubtaskTrees(Set<Task> tasks) {
            return List.of();
        }

        private boolean havePrecedingRelation(Task first, Task second) {
            return false;
        }

    }

}
