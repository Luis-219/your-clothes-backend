package pe.com.yourclothes.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.yourclothes.backend.entities.Product;
import pe.com.yourclothes.backend.entities.ProductImage;
import pe.com.yourclothes.backend.exceptions.ResourceNotFoundException;
import pe.com.yourclothes.backend.repositories.ProductImageRepository;
import pe.com.yourclothes.backend.repositories.ProductRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductImageController {

    @Autowired
    ProductImageRepository productImageRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products_images")
    public ResponseEntity<List<ProductImage>> getAllProductsImages(){
        List<ProductImage> productImages = productImageRepository.findAll();

        for(ProductImage productImage: productImages){
            productImage.setProduct(null);
        }
        return new ResponseEntity<List<ProductImage>>(productImages, HttpStatus.OK);
    }
    @PostMapping("/products_images/{id}")
    public ResponseEntity<ProductImage> createProductImage(@PathVariable("id") Long id, @RequestBody ProductImage image){

        Product foundProduct = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));
        foundProduct.setCartProducts(null);
        foundProduct.setShop(null);
        foundProduct.setProductImage(null);
        ProductImage newImage = productImageRepository.save(new ProductImage(
                foundProduct.getId(), image.getImg(), foundProduct
        ));
        return new ResponseEntity<ProductImage>(newImage, HttpStatus.CREATED);
    }

    @GetMapping("/products_images/{id}")
    public ResponseEntity<ProductImage> getProductImageById(@PathVariable("id") Long id)
    {
        ProductImage productImage = productImageRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Image not found"));;
        productImage.setProduct(null);
        return new ResponseEntity<ProductImage>(productImage, HttpStatus.OK);
    }

    @PutMapping("/products_images/{id}")
    public  ResponseEntity<ProductImage> updateProductImageById(@PathVariable("id") Long id, @RequestBody ProductImage image)
    {
        ProductImage foundProductImage = productImageRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Image not found"));

        if(image.getId_product() != null)
            foundProductImage.setId_product(image.getId_product());
        if(image.getImg() != null)
            foundProductImage.setImg(image.getImg());

        ProductImage updateImage = productImageRepository.save(foundProductImage);
        updateImage.setProduct(null);

        return new ResponseEntity<ProductImage>(updateImage, HttpStatus.OK);
    }
    @DeleteMapping("/products_images/{id}")
    public ResponseEntity<HttpStatus> deleteProductImageById(@PathVariable("id") Long id)
    {
        ProductImage foundImage = productImageRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Image not found"));
        foundImage.setProduct(null);
        productImageRepository.deleteById(foundImage.getId_product());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
