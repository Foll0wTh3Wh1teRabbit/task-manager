package ru.nsu.common.mapper;

import ru.nsu.common.dto.RelationDTO;
import ru.nsu.common.model.Relation;

public class RelationMapper {

    public static RelationDTO toRelationDTO(Relation relation) {
        RelationDTO relationDTO = new RelationDTO();

        relationDTO.setTaskFromId(relation.getTaskFrom().getId());
        relationDTO.setTaskToId(relation.getTaskTo().getId());
        relationDTO.setRelationType(relation.getRelationType());

        return relationDTO;
    }

}
