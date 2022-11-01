package pe.com.yourclothes.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pe.com.yourclothes.backend.entities.Shop;
import pe.com.yourclothes.backend.entities.User;
import pe.com.yourclothes.backend.repositories.ShopRepository;
import pe.com.yourclothes.backend.repositories.UserRepository;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(
			UserRepository userRepository,
			ShopRepository shopRepository
	){
		return	args -> {
			User user = new User("Luis", "Rios", "fan@gmail.com", "12345", "263", "346", "Jr. roses");
			userRepository.save(user);
			shopRepository.save(new Shop("try", "35326", "saf", "saf", user));

			User user2;
			Long id = Long.valueOf(1);
			user2= userRepository.findById(id).get();

			System.out.println(user2.getName());

			User user3= userRepository.findByDniAndName("263", "Luis");
			System.out.println(user3.getEmail());

		};
	}

}
