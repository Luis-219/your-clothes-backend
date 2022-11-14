package pe.com.yourclothes.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.yourclothes.backend.entities.Cart;
import pe.com.yourclothes.backend.entities.CartProduct;
import pe.com.yourclothes.backend.entities.Product;
import pe.com.yourclothes.backend.entities.User;
import pe.com.yourclothes.backend.exceptions.ResourceNotFoundException;
import pe.com.yourclothes.backend.repositories.CartProductRepository;
import pe.com.yourclothes.backend.repositories.CartRepository;
import pe.com.yourclothes.backend.repositories.ProductRepository;
import pe.com.yourclothes.backend.repositories.UserRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CartProductController {
    @Autowired
    private CartProductRepository cartProductRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/carts_products")
    public ResponseEntity<List<CartProduct>> getAllCartsProducts(){
        List<CartProduct> cartProducts = cartProductRepository.findAll();

        for(CartProduct cartProduct: cartProducts)
        {
            cartProduct.setProduct(null);
            cartProduct.setCart(null);
        }

        return new ResponseEntity<List<CartProduct>>(cartProducts, HttpStatus.OK);
    }
    @GetMapping("/carts_products/{id}")
    public ResponseEntity<CartProduct> getCartProductById(@PathVariable("id") Long id)
    {
        CartProduct cartProduct = cartProductRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("CartProduct not found"));
        cartProduct.setProduct(null);
        cartProduct.setCart(null);
        return new ResponseEntity<CartProduct>(cartProduct, HttpStatus.OK);
    }
    @GetMapping("/carts_products/cart_id/{id}")
    public ResponseEntity<List<CartProduct>> getCartByUser(@PathVariable("id") Long id)
    {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Cart not found"));
        List<CartProduct> cartProducts = cartProductRepository.findByCart(cart);
        return new ResponseEntity<List<CartProduct>>(cartProducts, HttpStatus.OK);
    }
    @PostMapping("/carts_products/cart_id/{cart_id}/product_id/{product_id}")
    public ResponseEntity<CartProduct> createCart(@PathVariable("cart_id") Long cart_id,@PathVariable("product_id") Long product_id, @RequestBody CartProduct cartProduct){

        Cart foundCart = cartRepository.findById(cart_id)
                .orElseThrow(()->new ResourceNotFoundException("Cart not found"));
        foundCart.setCartProducts(null);
        foundCart.setUser(null);
        Product foundProduct = productRepository.findById(product_id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));
        foundProduct.setShop(null);
        foundProduct.setCartProducts(null);

        CartProduct newCartProduct = cartProductRepository.save(new CartProduct(
                foundProduct.getId(),
                foundCart.getId(),
                cartProduct.getQuantity(),
                foundCart,
                foundProduct
        ));
        return new ResponseEntity<CartProduct>(newCartProduct, HttpStatus.CREATED);
    }
    @DeleteMapping("/carts_products/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable("id") Long id)
    {
        CartProduct foundCartProduct = cartProductRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("CartProduct not found"));

        cartProductRepository.deleteById(foundCartProduct.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/carts_products/{id}")
    public  ResponseEntity<CartProduct> updateCartById(@PathVariable("id") Long id, @RequestBody CartProduct cartProduct)
    {
        CartProduct foundCartProduct = cartProductRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("CartProduct not found"));

        if(cartProduct.getQuantity() != null)
             foundCartProduct.setQuantity(cartProduct.getQuantity());


        CartProduct updateCartProduct = cartProductRepository.save(foundCartProduct);
        updateCartProduct.setProduct(null);
        updateCartProduct.setCart(null);

        return new ResponseEntity<CartProduct>(updateCartProduct, HttpStatus.OK);
    }
}

