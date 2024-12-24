package ru.nsu.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.common.enumerated.RelationType;
import ru.nsu.common.model.Relation;
import ru.nsu.common.model.Task;

import java.util.Optional;

@Repository
public interface RelationRepository extends JpaRepository<Relation, Long> {

    Optional<Relation> deleteRelationByTaskFromAndTaskToAndRelationType(
        Task taskFrom,
        Task taskTo,
        RelationType relationType
    );

}
