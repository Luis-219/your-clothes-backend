package pe.com.yourclothes.backend.entities.catalogue2;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "shippings")
@Data
@NoArgsConstructor
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Shipping(String name) {
        this.name = name;
    }
}
