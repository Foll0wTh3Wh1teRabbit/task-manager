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

}
