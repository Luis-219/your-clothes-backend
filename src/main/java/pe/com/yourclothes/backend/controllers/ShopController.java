package pe.com.yourclothes.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.yourclothes.backend.entities.Product;
import pe.com.yourclothes.backend.entities.Shop;
import pe.com.yourclothes.backend.entities.User;
import pe.com.yourclothes.backend.repositories.ShopRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShopController {
    @Autowired
    private ShopRepository shopRepository;

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
    @PostMapping("/shops")
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop){
        Shop newShop = shopRepository.save(new Shop(
                shop.getName(),
                shop.getPhone(),
                shop.getAddress(),
                shop.getDescription(),
                shop.getUser()
        ));

        return new ResponseEntity<Shop>(newShop, HttpStatus.CREATED);
    }
    @GetMapping("/shops/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable("id") Long id)
    {
        Shop shop = shopRepository.findById(id).get();
        shop.getUser().setShop(null);
        for(Product product: shop.getProductList())
        {
            product.setShop(null);
            product.setCartProducts(null);
        }
        return new ResponseEntity<Shop>(shop, HttpStatus.OK);
    }
}
