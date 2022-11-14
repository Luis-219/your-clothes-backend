package pe.com.yourclothes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.yourclothes.backend.entities.catalogue.Pricetype;

public interface PricetypeRepository extends JpaRepository<Pricetype, Long> {
}
