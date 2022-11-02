package pe.com.yourclothes.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.yourclothes.backend.entities.CartProduct;
import pe.com.yourclothes.backend.entities.Product;
import pe.com.yourclothes.backend.entities.Shop;
import pe.com.yourclothes.backend.entities.User;
import pe.com.yourclothes.backend.exceptions.ResourceNotFoundException;
import pe.com.yourclothes.backend.repositories.CartProductRepository;
import pe.com.yourclothes.backend.repositories.ProductRepository;
import pe.com.yourclothes.backend.repositories.ShopRepository;
import pe.com.yourclothes.backend.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShopController {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartProductRepository cartProductRepository;

    @GetMapping("/shops")
    public ResponseEntity<List<Shop>> getAllShops(){
        List<Shop> shops = shopRepository.findAll();

        for(Shop shop: shops){
            shop.getUser().setShop(null);
            shop.getUser().setCart(null);
        }

        return new ResponseEntity<List<Shop>>(shops, HttpStatus.OK);
    }
    @GetMapping("/shops_complete")
    public ResponseEntity<List<Shop>> getAllShopsData()
    {
        List<Shop> shops = shopRepository.findAll();
        for(Shop shop: shops)
        {
            shop.getUser().setShop(null);
            shop.getUser().setCart(null);

            for (Product product: shop.getProductList())
            {
                product.setShop(null);
                product.setCartProducts(null);
            }
        }

        return new ResponseEntity<List<Shop>>(shops, HttpStatus.OK);
    }
    @PostMapping("/shops/{id}")
    public ResponseEntity<Shop> createShop(@PathVariable("id") Long id, @RequestBody Shop shop){

        User userOwner = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));;
        Shop newShop = shopRepository.save(new Shop(
                shop.getName(),
                shop.getPhone(),
                shop.getAddress(),
                shop.getDescription(),
                userOwner
        ));
        return new ResponseEntity<Shop>(newShop, HttpStatus.CREATED);
    }
    @GetMapping("/shops/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable("id") Long id)
    {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Shop not found"));;
        shop.getUser().setShop(null);
        for(Product product: shop.getProductList())
        {
            product.setShop(null);
            product.setCartProducts(null);
        }
        return new ResponseEntity<Shop>(shop, HttpStatus.OK);
    }
    @PutMapping("/shops/{id}")
    public  ResponseEntity<Shop> updateShopById(@PathVariable("id") Long id, @RequestBody Shop shop)
    {
        Shop foundShop = shopRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Shop not found"));;

        if(shop.getName() != null)
            foundShop.setName(shop.getName());
        if(shop.getPhone() != null)
            foundShop.setPhone(shop.getPhone());
        if(shop.getAddress() != null)
            foundShop.setAddress(shop.getAddress());
        if(shop.getDescription() != null)
            foundShop.setDescription(shop.getDescription());

        Shop updateShop = shopRepository.save(foundShop);
        updateShop.setProductList(null);
        updateShop.setUser(null);

        return new ResponseEntity<Shop>(updateShop, HttpStatus.OK);
    }
    @DeleteMapping("/shops/{id}")
    public ResponseEntity<HttpStatus> deleteShopByID(@PathVariable("id") Long id)
    {
        Shop foundShop = shopRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Shop not found"));;
        for(Product product: foundShop.getProductList())
        {
            for (CartProduct cartProduct: product.getCartProducts())
            {
                cartProductRepository.deleteById(cartProduct.getId());
            }
            productRepository.deleteById(product.getId());
        }

        shopRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
