package ru.nsu.common.mapper;

import ru.nsu.common.dto.UnboundRelationDTO;
import ru.nsu.common.model.Relation;
import ru.nsu.common.model.Task;

public class UnboundRelationMapper {

    public static Relation toRelation(Task taskFrom, Task taskTo, UnboundRelationDTO unboundRelationDTO) {
        Relation relation = new Relation();

        relation.setTaskFrom(taskFrom);
        relation.setTaskTo(taskTo);
        relation.setRelationType(unboundRelationDTO.getRelationType());

        return relation;
    }

}
