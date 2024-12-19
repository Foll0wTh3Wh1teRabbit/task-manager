package ru.nsu.common.mapper;

import ru.nsu.common.dto.RelationDTO;
import ru.nsu.common.dto.UnboundRelationDTO;
import ru.nsu.common.model.Relation;

public class RelationMapper {

    public static RelationDTO toRelationDTO(Relation relation) {
        RelationDTO relationDTO = new RelationDTO();

        relationDTO.setTaskFromId(relation.getTaskFrom().getId());
        relationDTO.setTaskToId(relation.getTaskTo().getId());
        relationDTO.setRelationType(relation.getRelationType());

        return relationDTO;
    }

    public static UnboundRelationDTO toUnboundRelationDTO(RelationDTO relationDTO) {
        UnboundRelationDTO unboundRelationDTO = new UnboundRelationDTO();

        unboundRelationDTO.setTaskToId(relationDTO.getTaskToId());
        unboundRelationDTO.setRelationType(relationDTO.getRelationType());

        return unboundRelationDTO;
    }

}
