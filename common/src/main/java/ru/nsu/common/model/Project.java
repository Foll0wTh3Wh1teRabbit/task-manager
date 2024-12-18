package ru.nsu.common.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nsu.common.dto.RelationDTO;
import ru.nsu.common.dto.TaskDTO;
import ru.nsu.common.mapper.RelationMapper;
import ru.nsu.common.mapper.TaskMapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(
        mappedBy = "project",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    private Set<Task> tasks;

    @PrePersist
    public void prePersist() {
        this.tasks = new HashSet<>();
    }

    public Set<TaskDTO> getTasksOfProject() {
        return this.getTasks()
            .stream()
            .map(TaskMapper::toTaskDTO)
            .collect(Collectors.toSet());
    }

}
