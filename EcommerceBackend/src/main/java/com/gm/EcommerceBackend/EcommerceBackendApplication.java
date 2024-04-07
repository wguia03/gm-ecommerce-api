package com.gm.EcommerceBackend;

import com.gm.EcommerceBackend.cart.Cart;
import com.gm.EcommerceBackend.cart.CartRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceBackendApplication.class, args);
	}

 @Bean
	public CommandLineRunner commandLineRunner(CartRepository cartRepository){
		return args -> {
			var cart = new Cart();
			cartRepository.save(cart);
		};
	}
}
