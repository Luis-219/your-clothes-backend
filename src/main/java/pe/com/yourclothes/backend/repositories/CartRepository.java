package pe.com.yourclothes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.yourclothes.backend.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
