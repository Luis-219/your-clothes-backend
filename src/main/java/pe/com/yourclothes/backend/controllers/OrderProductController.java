package pe.com.yourclothes.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.yourclothes.backend.entities.*;
import pe.com.yourclothes.backend.exceptions.ResourceNotFoundException;
import pe.com.yourclothes.backend.repositories.OrderProductRepository;
import pe.com.yourclothes.backend.repositories.OrderRepository;
import pe.com.yourclothes.backend.repositories.ProductRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class OrderProductController {

    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/orders_products/order_id/{order_id}/product_id/{product_id}")
    public ResponseEntity<OrderProduct> createOrderProduct(@PathVariable("order_id") Long order_id, @PathVariable("product_id") Long product_id, @RequestBody OrderProduct orderProduct){

        Order foundOrder = orderRepository.findById(order_id)
                .orElseThrow(()->new ResourceNotFoundException("Order not found"));
        foundOrder.setUser(null);
        foundOrder.setOrderProductList(null);

        Product foundProduct = productRepository.findById(product_id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));
        foundProduct.setShop(null);
        foundProduct.setCartProducts(null);

        OrderProduct newOrderProduct = orderProductRepository.save(new OrderProduct(
                foundOrder.getId(), foundProduct.getId(), orderProduct.getId_shop(), orderProduct.getProduct(),
                orderProduct.getQuantity(), orderProduct.getTotalprice(), foundOrder, foundProduct
        ));
        newOrderProduct.setObj_product(null);
        newOrderProduct.setOrder(null);
        return new ResponseEntity<OrderProduct>(newOrderProduct, HttpStatus.CREATED);
    }

    @GetMapping("/orders_products")
    public ResponseEntity<List<OrderProduct>> getAllOrdersProducts(){
        List<OrderProduct> orderProducts = orderProductRepository.findAll();

        for(OrderProduct orderProduct: orderProducts)
        {
            orderProduct.setObj_product(null);
            orderProduct.setOrder(null);
        }

        return new ResponseEntity<List<OrderProduct>>(orderProducts, HttpStatus.OK);
    }

}
