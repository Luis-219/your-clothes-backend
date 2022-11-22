package pe.com.yourclothes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.yourclothes.backend.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
