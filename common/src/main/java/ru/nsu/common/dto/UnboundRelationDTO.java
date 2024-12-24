package ru.nsu.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nsu.common.enumerated.RelationType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnboundRelationDTO {

    private Long taskToId;

    private RelationType relationType;

    public RelationType getReversedRelationType() {
        return switch (relationType) {
            case PRECEDES -> RelationType.FOLLOWS;
            case FOLLOWS -> RelationType.PRECEDES;

            case IS_PART_OF -> RelationType.CONTAINS;
            case CONTAINS -> RelationType.IS_PART_OF;
        };
    }

}
