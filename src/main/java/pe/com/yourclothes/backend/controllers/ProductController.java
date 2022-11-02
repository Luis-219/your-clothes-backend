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

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private CartProductRepository cartProductRepository;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productRepository.findAll();

        for(Product product: products){
            product.getShop().setProductList(null);
            product.setCartProducts(null);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
    @PostMapping("/products/{id}")
    public ResponseEntity<Product> createProduct(@PathVariable("id") Long id, @RequestBody Product product){

        Shop foundShop = shopRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Shop not found"));;
        Product newProduct = productRepository.save(new Product(
                product.getName(),
                product.getPrice(),
                product.getSize(),
                product.getBrand(),
                product.getSeason(),
                product.getQuantity(),
                foundShop
        ));
        return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id)
    {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));;
        product.getShop().setUser(null);
        product.getShop().setProductList(null);
        product.setCartProducts(null);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
    @GetMapping("products/shop_id/{id}")
    public ResponseEntity<List<Product>> getProductsByShop(@PathVariable("id") Long id)
    {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Shop not found"));;
        List<Product> products = productRepository.findByShop(shop);
        for(Product product: products)
        {
            product.setShop(null);
            product.setCartProducts(null);
        }

        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
    @PutMapping("/products/{id}")
    public  ResponseEntity<Product> updateProductById(@PathVariable("id") Long id, @RequestBody Product product)
    {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));

        if(product.getName() != null)
            foundProduct.setName(product.getName());
        if(product.getPrice() != null)
            foundProduct.setPrice(product.getPrice());
        if(product.getSize() != null)
            foundProduct.setSize(product.getSize());
        if(product.getBrand() != null)
            foundProduct.setBrand(product.getBrand());
        if(product.getSeason() != null)
            foundProduct.setSeason(product.getSeason());
        if(product.getQuantity() != null)
            foundProduct.setSeason(product.getSeason());

        Product updateProduct = productRepository.save(foundProduct);
        updateProduct.setCartProducts(null);
        updateProduct.getShop().setProductList(null);
        updateProduct.getShop().setUser(null);

        return new ResponseEntity<Product>(updateProduct, HttpStatus.OK);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable("id") Long id)
    {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));
        for(CartProduct cartProduct: foundProduct.getCartProducts())
        {
            cartProductRepository.deleteById(cartProduct.getId());
        }
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
