package pe.com.yourclothes.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pe.com.yourclothes.backend.entities.Cart;
import pe.com.yourclothes.backend.entities.Product;
import pe.com.yourclothes.backend.entities.Shop;
import pe.com.yourclothes.backend.entities.User;
import pe.com.yourclothes.backend.entities.catalogue.*;
import pe.com.yourclothes.backend.repositories.*;

import java.util.Date;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(
			UserRepository userRepository,
			CartRepository cartRepository,
			ShopRepository shopRepository,
			ProductRepository productRepository,
			ConditionRepository conditionRepository,
			GenderRepository genderRepository,
			SizeRepository sizeRepository,
			MaterialRepository materialRepository,
			TypeRepository typeRepository,
			SeasonRepository seasonRepository,
			PricetypeRepository pricetypeRepository
	){
		return	args -> {
			User user = new User("Luis", "Rios", "fan@gmail.com", "12345", "263", "346", "Jr. roses");
			userRepository.save(user);
			Cart cart = new Cart(user.getId(), 0.00, 0, user);
			cartRepository.save(cart);
			Shop shop = new Shop(user.getId(), "try", "253", "saf", "saf", 0, "0", user);
			shopRepository.save(shop);
			Product product = new Product(shop.getId(), "Pants", shop.getName(), new Date(), "Disponible", 1, 100.00,
					"Hombre", "M", "Lana", "Pants", "Cuero", "Verano", "2020",
					"Oferta", shop);
			productRepository.save(product);
			User user2;
			Long id = Long.valueOf(1);
			user2= userRepository.findById(id).get();

			Condition condition = new Condition("Disponible");
			Condition condition1 = new Condition("Agotado");
			conditionRepository.save(condition);
			conditionRepository.save(condition1);

			Gender gender = new Gender("Hombre");
			Gender gender1 = new Gender("Mujer");
			Gender gender2 = new Gender("Ambos");
			Gender gender3 = new Gender("Niños");
			genderRepository.save(gender);
			genderRepository.save(gender1);
			genderRepository.save(gender2);
			genderRepository.save(gender3);

			Size size = new Size("XS"); sizeRepository.save(size);
			Size size1 = new Size("S"); sizeRepository.save(size1);
			Size size2 = new Size("M"); sizeRepository.save(size2);
			Size size3 = new Size("L"); sizeRepository.save(size3);
			Size size4 = new Size("XL");sizeRepository.save(size4);
			Size size5 = new Size("XLL");sizeRepository.save(size5);

			Material material = new Material("Algodón"); materialRepository.save(material);
			Material material1 = new Material("Poliéster"); materialRepository.save(material1);
			Material material2 = new Material("Lino"); materialRepository.save(material2);
			Material material3 = new Material("Lana"); materialRepository.save(material3);
			Material material4 = new Material("Seda"); materialRepository.save(material4);
			Material material5 = new Material("Nylon"); materialRepository.save(material5);
			Material material6 = new Material("Lycra"); materialRepository.save(material6);
			Material material7 = new Material("Cuero"); materialRepository.save(material7);

			Type type = new Type("Casaca"); typeRepository.save(type);
			Type type1 = new Type("Pantalón"); typeRepository.save(type1);
			Type type2 = new Type("Camiseta"); typeRepository.save(type2);
			Type type3 = new Type("Short"); typeRepository.save(type3);
			Type type4 = new Type("Zapatilla"); typeRepository.save(type4);
			Type type5 = new Type("Camisa"); typeRepository.save(type5);
			Type type6 = new Type("Chompa"); typeRepository.save(type6);
			Type type7 = new Type("Otro"); typeRepository.save(type7);

			Season season = new Season("Verano"); seasonRepository.save(season);
			Season season1 = new Season("Invierno"); seasonRepository.save(season1);
			Season season2 = new Season("Otoño"); seasonRepository.save(season2);
			Season season3 = new Season("Primavera"); seasonRepository.save(season3);

			Pricetype pricetype = new Pricetype("Oferta"); pricetypeRepository.save(pricetype);
			Pricetype pricetype1 = new Pricetype("Normal"); pricetypeRepository.save(pricetype1);


			System.out.println(user2.getName());
			System.out.println(cart.getUser().getName());
		};
	}

}
