package pe.com.yourclothes.backend.entities.catalogue;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "pricetypes")
@Data
@NoArgsConstructor
public class Pricetype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Pricetype(String name) {
        this.name = name;
    }
}



