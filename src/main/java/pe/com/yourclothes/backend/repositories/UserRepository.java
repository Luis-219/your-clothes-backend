package pe.com.yourclothes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.yourclothes.backend.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByDni(String dni);

    User findByDniAndName(String dni, String name);
    User findByEmail(String email);
}
