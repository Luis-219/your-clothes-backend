package pe.com.yourclothes.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "products_images")
@Data
@NoArgsConstructor

public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_product;
    @Column(columnDefinition="TEXT", length = 1000)
    private String img;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public ProductImage(Long id_product, String img, Product product) {
        this.id_product = id_product;
        this.img = img;
        this.product = product;
    }
}
