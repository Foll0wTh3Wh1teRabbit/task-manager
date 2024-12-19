package ru.nsu.taskservice.task.service;

import ru.nsu.common.dto.RelationDTO;
import ru.nsu.common.dto.TaskDTO;
import ru.nsu.common.dto.UnboundRelationDTO;

import java.util.List;

public interface RelationService {

    TaskDTO modifyRelations(
        Long taskId,
        List<RelationDTO> relationsToRemove,
        List<UnboundRelationDTO> relationsToAdd
    );

}
