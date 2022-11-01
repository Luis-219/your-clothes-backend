package pe.com.yourclothes.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String shop_name;
    private Double price;
    private String size;
    private String brand;
    private String season;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToMany(mappedBy = "product")
    private List<CartProduct> cartProducts;

    public Product(String name, String shop_name, Double price, String size, String brand, String season, Integer quantity ,Shop shop) {
        this.name = name;
        this.shop_name = shop_name;
        this.price = price;
        this.size = size;
        this.brand = brand;
        this.season = season;
        this.shop = shop;
        this.quantity = quantity;
    }
}
