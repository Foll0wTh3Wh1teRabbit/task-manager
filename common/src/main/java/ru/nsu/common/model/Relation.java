package ru.nsu.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nsu.common.enumerated.RelationType;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "relations")
public class Relation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "relation_type", nullable = false)
    private RelationType relationType;

    @ManyToOne
    @JoinColumn(name = "task_from_id", referencedColumnName = "id", nullable = false)
    private Task taskFrom;

    @ManyToOne
    @JoinColumn(name = "task_to_id", referencedColumnName = "id", nullable = false)
    private Task taskTo;

    public Relation(Task taskFrom, Task taskTo, RelationType relationType) {
        this.taskFrom = taskFrom;
        this.taskTo = taskTo;
        this.relationType = relationType;
    }

}
