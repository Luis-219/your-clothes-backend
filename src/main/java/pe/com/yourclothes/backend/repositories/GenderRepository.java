package pe.com.yourclothes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.yourclothes.backend.entities.catalogue.Gender;

public interface GenderRepository extends JpaRepository<Gender, Long> {
}
