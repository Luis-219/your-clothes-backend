package pe.com.yourclothes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.yourclothes.backend.entities.catalogue.Size;

public interface SizeRepository extends JpaRepository<Size, Long> {
}
