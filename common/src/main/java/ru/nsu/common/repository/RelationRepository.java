package ru.nsu.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.common.model.Relation;

@Repository
public interface RelationRepository extends JpaRepository<Relation, Long> {
}
