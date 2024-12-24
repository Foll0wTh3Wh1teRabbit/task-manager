package ru.nsu.taskservice.task.service;

import ru.nsu.common.dto.UnboundRelationDTO;
import ru.nsu.common.model.Task;

import java.util.List;

public interface RelationModificationService {

    void addRelations(Task task, List<UnboundRelationDTO> relationsToAdd);

    void removeRelations(Task task, List<UnboundRelationDTO> relationsToRemove);

}
