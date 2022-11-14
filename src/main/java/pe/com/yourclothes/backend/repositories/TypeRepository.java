package pe.com.yourclothes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.yourclothes.backend.entities.catalogue.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
