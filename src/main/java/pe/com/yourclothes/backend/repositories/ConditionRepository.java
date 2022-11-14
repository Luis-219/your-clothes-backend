package pe.com.yourclothes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.yourclothes.backend.entities.catalogue.Condition;

public interface ConditionRepository extends JpaRepository<Condition, Long> {
}
