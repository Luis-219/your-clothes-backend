package pe.com.yourclothes.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.yourclothes.backend.entities.*;
import pe.com.yourclothes.backend.repositories.CartProductRepository;
import pe.com.yourclothes.backend.repositories.CartRepository;
import pe.com.yourclothes.backend.repositories.ProductRepository;
import pe.com.yourclothes.backend.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartProductRepository cartProductRepository;

    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> getAllCarts(){
        List<Cart> carts = cartRepository.findAll();

        for(Cart cart: carts){
            for(CartProduct cartProduct: cart.getCartProducts())
            {
                cartProduct.setCart(null);
                cartProduct.getProduct().setCartProducts(null);
                cartProduct.getProduct().setShop(null);
            }
            cart.getUser().setCart(null);
            cart.getUser().setShop(null);
        }
        return new ResponseEntity<List<Cart>>(carts, HttpStatus.OK);
    }
    @GetMapping("/carts/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("id") Long id)
    {
        Cart cart = cartRepository.findById(id).get();
        cart.setCartProducts(null);
        cart.getUser().setShop(null);
        cart.getUser().setCart(null);

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }
    @GetMapping("/carts/user_id/{id}")
    public ResponseEntity<Cart> getCartByUser(@PathVariable("id") Long id)
    {
        User user = userRepository.findById(id).get();
        Cart cart = cartRepository.findByUser(user);
        cart.setCartProducts(null);
        cart.getUser().setShop(null);
        cart.getUser().setCart(null);

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }
    @PostMapping("/carts/user_id/{id}")
    public ResponseEntity<Cart> createCart(@PathVariable("id") Long id, @RequestBody Cart cart){

        User foundUser = userRepository.findById(id).get();
        Cart newCart = cartRepository.save(new Cart(
                cart.getTotal_purchase(),
                cart.getQuantity_products(),
                foundUser
        ));
        return new ResponseEntity<Cart>(newCart, HttpStatus.CREATED);
    }
    @PutMapping("/carts/{id}")
    public  ResponseEntity<Cart> updateCartById(@PathVariable("id") Long id, @RequestBody Cart cart)
    {
        Cart foundCart = cartRepository.findById(id).get();

        if(cart.getTotal_purchase() != null)
            foundCart.setTotal_purchase(cart.getTotal_purchase());
        if(cart.getQuantity_products() != null)
            foundCart.setQuantity_products(cart.getQuantity_products());


        Cart updateCart = cartRepository.save(foundCart);
        updateCart.setCartProducts(null);
        updateCart.setUser(null);

        return new ResponseEntity<Cart>(updateCart, HttpStatus.OK);
    }
    @DeleteMapping("/carts/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable("id") Long id)
    {
        Cart foundCart = cartRepository.findById(id).get();
        for(CartProduct cartProduct: foundCart.getCartProducts())
        {
            cartProductRepository.deleteById(cartProduct.getId());
        }
        cartRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
