package pe.com.yourclothes.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Long id_user;
    private Double total_purchase;
    private Integer quantity_products;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartProduct> cartProducts;


    public Cart(Long id_user, Double total_purchase, Integer quantity_products, User user) {
        this.id_user = id_user;
        this.total_purchase = total_purchase;
        this.quantity_products = quantity_products;
        this.user = user;
    }
}
