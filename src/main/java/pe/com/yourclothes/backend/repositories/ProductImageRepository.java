package pe.com.yourclothes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.yourclothes.backend.entities.Product;
import pe.com.yourclothes.backend.entities.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    ProductImage findByProduct(Product product);
}
