package pe.com.yourclothes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.yourclothes.backend.entities.CartProduct;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
}
