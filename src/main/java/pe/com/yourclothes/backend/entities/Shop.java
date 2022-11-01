package pe.com.yourclothes.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shops")
@Data
@NoArgsConstructor

public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String description;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "shop")
    private List<Product> productList;

    public Shop(String name, String phone, String address, String description, User user) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.description = description;
        this.user = user;
    }
}

