package ru.nsu.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nsu.common.model.Task;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private Long id;

    private String name;

    private Set<TaskDTO> tasks;

}
