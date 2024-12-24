package ru.nsu.taskservice.task.service;

import ru.nsu.common.dto.TaskDTO;
import ru.nsu.common.dto.UnboundRelationDTO;

import java.util.List;

public interface RelationService {

    TaskDTO modifyRelations(
        Long taskFromId,
        List<UnboundRelationDTO> relationsToRemove,
        List<UnboundRelationDTO> relationsToAdd
    );

}
