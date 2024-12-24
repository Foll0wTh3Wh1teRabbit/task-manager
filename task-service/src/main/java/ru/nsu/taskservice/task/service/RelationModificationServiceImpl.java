package ru.nsu.taskservice.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.common.dto.UnboundRelationDTO;
import ru.nsu.common.model.Relation;
import ru.nsu.common.model.Task;
import ru.nsu.common.repository.RelationRepository;
import ru.nsu.common.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RelationModificationServiceImpl implements RelationModificationService {

    private final TaskRepository taskRepository;

    private final RelationRepository relationRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRelations(Task task, List<UnboundRelationDTO> relationsToAdd) {
        Optional.ofNullable(relationsToAdd)
            .ifPresent(
                relations -> relations.forEach(
                    relation -> {
                        Task otherTask = taskRepository.findById(relation.getTaskToId())
                            .orElseThrow(() -> new IllegalArgumentException("Another task not found"));

                        Relation relationFrom = new Relation(task, otherTask, relation.getRelationType());
                        Relation relationTo = new Relation(otherTask, task, relation.getReversedRelationType());

                        relationRepository.saveAndFlush(relationFrom);
                        relationRepository.saveAndFlush(relationTo);
                    }
                )
            );
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void removeRelations(Task task, List<UnboundRelationDTO> relationsToRemove) {
        Optional.ofNullable(relationsToRemove)
            .ifPresent(
                relations -> relations.forEach(
                    relation -> {
                        Task otherTask = taskRepository.findById(relation.getTaskToId())
                            .orElseThrow(() -> new IllegalArgumentException("Another task not found"));

                        relationRepository.deleteRelationByTaskFromAndTaskToAndRelationType(
                            task,
                            otherTask,
                            relation.getRelationType()
                        ).orElseThrow(() -> new IllegalArgumentException("Relation from not found"));

                        relationRepository.deleteRelationByTaskFromAndTaskToAndRelationType(
                            otherTask,
                            task,
                            relation.getReversedRelationType()
                        ).orElseThrow(() -> new IllegalArgumentException("Relation to not found"));

                        relationRepository.flush();
                    }
                )
            );
    }

}
