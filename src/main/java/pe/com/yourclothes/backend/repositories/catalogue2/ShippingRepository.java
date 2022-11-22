package pe.com.yourclothes.backend.repositories.catalogue2;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.yourclothes.backend.entities.catalogue2.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
}
