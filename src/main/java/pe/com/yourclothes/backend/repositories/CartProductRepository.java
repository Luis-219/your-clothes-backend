package pe.com.yourclothes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.yourclothes.backend.entities.Cart;
import pe.com.yourclothes.backend.entities.CartProduct;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    List<CartProduct> findByCart(Cart cart);
}
