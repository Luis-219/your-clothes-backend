package pe.com.yourclothes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.yourclothes.backend.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE id=?1", nativeQuery = true)
    User findByIdSQL(String id);

    @Query(value = "SELECT * FROM users WHERE email=?1 and password=?2", nativeQuery = true)
    User findByEmailAndPasswordSQL(String email, String password);

    @Query(value = "SELECT * FROM users WHERE email=?1", nativeQuery = true)
    User findByEmailSQL(String email);
}
