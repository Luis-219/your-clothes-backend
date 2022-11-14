package pe.com.yourclothes.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carts_products")
@Data
@NoArgsConstructor
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Long product_id;
    private Long shopcart_id;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "id_cart")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;


    public CartProduct(Long product_id, Long shopcart_id, Integer quantity, Cart cart, Product product) {
        this.product_id = product_id;
        this.shopcart_id = shopcart_id;
        this.quantity = quantity;
        this.cart = cart;
        this.product = product;
    }
}
