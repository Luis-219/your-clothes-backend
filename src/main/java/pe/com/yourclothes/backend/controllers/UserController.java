package pe.com.yourclothes.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.yourclothes.backend.entities.*;
import pe.com.yourclothes.backend.repositories.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartProductRepository cartProductRepository;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> users;
        users = userRepository.findAll();

        for(User u: users)
        {
            u.setShop(null);
            u.setCart(null);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    @GetMapping("/users_complete")
    public ResponseEntity<List<User>> getAllUsersData()
    {
        List<User> users;
        users = userRepository.findAll();

        for(User u: users)
        {
            if(u.getShop() != null)
            {
                u.getShop().setUser(null);
                u.getShop().setProductList(null);
            }
            else u.setShop(null);
            if(u.getCart() != null)
            {
                u.getCart().setUser(null);
                u.getCart().setCartProducts(null);
            }
            else u.setCart(null);
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser = userRepository.save(new User(user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                user.getDni(),
                user.getPhone(),
                user.getAddress()));
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id)
    {
        User user = userRepository.findById(id).get();

        if(user.getShop() != null){
            user.getShop().setUser(null);
            user.getShop().setProductList(null);
        }
        if(user.getCart() != null)
        {
            user.getCart().setUser(null);
            user.getCart().setCartProducts(null);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    @GetMapping("/users/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email)
    {
        User user = userRepository.findByEmail(email);

        if(user.getShop() != null){
            user.getShop().setUser(null);
            user.getShop().setProductList(null);
        }
        if(user.getCart() != null)
        {
            user.getCart().setUser(null);
            user.getCart().setCartProducts(null);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable("id") Long id, @RequestBody User user)
    {
        User foundUser = userRepository.findById(id).get();
        if(user.getName() != null)
            foundUser.setName(user.getName());
        if(user.getLastname() != null)
            foundUser.setLastname(user.getLastname());
        if(user.getEmail() != null)
            foundUser.setEmail(user.getEmail());
        if(user.getPassword() != null)
            foundUser.setPassword(user.getPassword());
        if(user.getDni() != null)
            foundUser.setDni(user.getDni());
        if(user.getPhone() != null)
            foundUser.setPhone(user.getPhone());
        if(user.getAddress() != null)
            foundUser.setAddress(user.getAddress());

        User updatedUser = userRepository.save(foundUser);
        updatedUser.setShop(null);
        updatedUser.setCart(null);

        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") Long id)
    {
        User foundUser = userRepository.findById(id).get();
        if(foundUser.getShop() != null)
        {
            for(Product product: foundUser.getShop().getProductList())
            {
                for(CartProduct cartProduct: product.getCartProducts())
                {
                    cartProductRepository.deleteById(cartProduct.getId());
                }
                productRepository.deleteById(product.getId());
            }
            shopRepository.deleteById(foundUser.getShop().getId());
        }
        if(foundUser.getCart() != null)
        {
            for(CartProduct cartProduct: foundUser.getCart().getCartProducts())
            {
                cartRepository.deleteById(cartProduct.getId());
            }
            cartRepository.deleteById(foundUser.getCart().getId());
        }

        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
