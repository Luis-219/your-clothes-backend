package pe.com.yourclothes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.yourclothes.backend.entities.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {

}
