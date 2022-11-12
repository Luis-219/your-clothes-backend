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
    private Long idUser;
    private String name;
    private String phone;
    private String adress;
    private String descripción;
    private Integer amountProducts;
    private String aceptación;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "shop")
    private List<Product> productList;


    public Shop( Long idUser, String name, String phone, String adress, String descripción, Integer amountProducts, String aceptación, User user) {
        this.name = name;
        this.idUser = idUser;
        this.phone = phone;
        this.adress = adress;
        this.descripción = descripción;
        this.amountProducts = amountProducts;
        this.aceptación = aceptación;
        this.user = user;
    }
}

