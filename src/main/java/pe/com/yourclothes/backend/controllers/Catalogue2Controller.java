package pe.com.yourclothes.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.yourclothes.backend.entities.catalogue2.Payment;
import pe.com.yourclothes.backend.entities.catalogue2.Shipping;
import pe.com.yourclothes.backend.repositories.catalogue2.PaymentRepository;
import pe.com.yourclothes.backend.repositories.catalogue2.ShippingRepository;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class Catalogue2Controller {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    ShippingRepository shippingRepository;

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getPayments()
    {
        List<Payment> payments = paymentRepository.findAll();
        return new ResponseEntity<List<Payment>>(payments, HttpStatus.OK);
    }
    @GetMapping("/shippings")
    public ResponseEntity<List<Shipping>> getShippings()
    {
        List<Shipping> shippings = shippingRepository.findAll();
        return new ResponseEntity<List<Shipping>>(shippings, HttpStatus.OK);
    }
}
