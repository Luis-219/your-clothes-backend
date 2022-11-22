package pe.com.yourclothes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.yourclothes.backend.entities.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

}
