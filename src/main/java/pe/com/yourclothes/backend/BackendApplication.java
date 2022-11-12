package pe.com.yourclothes.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pe.com.yourclothes.backend.entities.Product;
import pe.com.yourclothes.backend.entities.Shop;
import pe.com.yourclothes.backend.entities.User;
import pe.com.yourclothes.backend.repositories.ProductRepository;
import pe.com.yourclothes.backend.repositories.ShopRepository;
import pe.com.yourclothes.backend.repositories.UserRepository;

import java.util.Date;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(
			UserRepository userRepository,
			ShopRepository shopRepository,
			ProductRepository productRepository
	){
		return	args -> {
			User user = new User("Luis", "Rios", "fan@gmail.com", "12345", "263", "346", "Jr. roses");
			userRepository.save(user);
			Shop shop = new Shop(user.getId(), "try", "253", "saf", "saf", 0, "0", user);
			shopRepository.save(shop);
			Product product = new Product(shop.getId(), "Pants", shop.getName(), new Date(), "Disponible", 1, 100.00,
					"Hombre", "M", "Lana", "Pants", "Cuero", "Verano", "2020",
					"Oferta", shop);
			productRepository.save(product);
			User user2;
			Long id = Long.valueOf(1);
			user2= userRepository.findById(id).get();

			System.out.println(user2.getName());


		};
	}

}
