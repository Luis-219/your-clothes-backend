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

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
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
            product.setShop(null);
            product.setCartProducts(null);
            product.setProductImage(null);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
    @PostMapping("/products/{id}")
    public ResponseEntity<Product> createProduct(@PathVariable("id") Long id, @RequestBody Product product){

        Shop foundShop = shopRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Shop not found"));
        foundShop.setUser(null);
        foundShop.setProductList(null);
        Product newProduct = productRepository.save(new Product(
                foundShop.getId(),
                product.getName(),
                product.getShopname(),
                new Date(),
                product.getCondition(),
                product.getQuantity(),
                product.getPrice(),
                product.getGender(),
                product.getSize(),
                product.getMaterial(),
                product.getBrand(),
                product.getType(),
                product.getSeason(),
                product.getYear(),
                product.getPricetype(),
                foundShop,
                product.getProductImage()
        ));
        return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id)
    {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));;
        product.setShop(null);
        product.setCartProducts(null);
        product.setProductImage(null);
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
            product.setProductImage(null);
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
        if(product.getShopname() != null)
            foundProduct.setShopname(product.getShopname());
        if(product.getCondition() != null)
            foundProduct.setCondition(product.getCondition());
        if(product.getQuantity() != null)
            foundProduct.setQuantity(product.getQuantity());
        if(product.getPrice() != null)
            foundProduct.setPrice(product.getPrice());
        if(product.getSize() != null)
            foundProduct.setSize(product.getSize());
        if(product.getMaterial() != null)
            foundProduct.setMaterial(product.getMaterial());
        if(product.getBrand() != null)
            foundProduct.setBrand(product.getBrand());
        if(product.getType() != null)
            foundProduct.setType(product.getType());
        if(product.getSeason() != null)
            foundProduct.setSeason(product.getSeason());
        if(product.getYear() != null)
            foundProduct.setYear(product.getYear());
        if(product.getPricetype() != null)
            foundProduct.setPricetype(product.getPricetype());


        Product updateProduct = productRepository.save(foundProduct);
        updateProduct.setCartProducts(null);
        updateProduct.setShop(null);
        updateProduct.setProductImage(null);

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
