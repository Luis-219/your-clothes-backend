package pe.com.yourclothes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.yourclothes.backend.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
