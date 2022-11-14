package pe.com.yourclothes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.yourclothes.backend.entities.catalogue.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
