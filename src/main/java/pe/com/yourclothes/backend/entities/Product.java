package pe.com.yourclothes.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Long idShop;
    private String name;
    private String shopname;
    private Date pubdate;
    private String condition;
    private Integer quantity;
    private Double price;
    private String gender;
    private String size;
    private String material;
    private String brand;
    private String type;
    private String season;
    private String year;
    private String pricetype;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToOne(mappedBy = "product")
    private ProductImage productImage;

    @OneToMany(mappedBy = "product")
    private List<CartProduct> cartProducts;


    public Product(Long idShop, String name, String shopname, Date pubdate, String condition, Integer quantity, Double price, String gender, String size, String material, String brand, String type, String season, String year, String pricetype, Shop shop, ProductImage productImage) {
        this.idShop = idShop;
        this.name = name;
        this.shopname = shopname;
        this.pubdate = pubdate;
        this.condition = condition;
        this.quantity = quantity;
        this.price = price;
        this.gender = gender;
        this.size = size;
        this.material = material;
        this.brand = brand;
        this.type = type;
        this.season = season;
        this.year = year;
        this.pricetype = pricetype;
        this.shop = shop;
        this.productImage = productImage;
    }
}
