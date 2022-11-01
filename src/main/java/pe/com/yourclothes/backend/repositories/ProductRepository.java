package pe.com.yourclothes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.yourclothes.backend.entities.Product;
import pe.com.yourclothes.backend.entities.Shop;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByShop(Shop shop);
}
