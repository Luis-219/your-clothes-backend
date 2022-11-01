package pe.com.yourclothes.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;
    private String email;
    private String password;
    private String dni;
    private String phone;
    private String address;

    @OneToOne(mappedBy = "user")
    private Shop shop;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    public User(String name, String lastname, String email, String password, String dni, String phone, String address) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.dni = dni;
        this.phone = phone;
        this.address = address;
    }
}
