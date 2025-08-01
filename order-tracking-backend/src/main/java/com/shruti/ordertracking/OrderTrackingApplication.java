package com.shruti.ordertracking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shruti.ordertracking.entities.User;
import com.shruti.ordertracking.repositories.UserRepository;

@SpringBootApplication
public class OrderTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderTrackingApplication.class, args);
	}

	@Bean
	public CommandLineRunner createTestUser(UserRepository repo, PasswordEncoder encoder) {
		return args -> {
			repo.findByUsername("shruti").ifPresentOrElse(user -> {
				// Optional: log that user already exists
				System.out.println("User already exists: " + user.getUsername());
			}, () -> {
				User user = new User();
				user.setUsername("shruti");
				user.setPassword(encoder.encode("password"));
				user.setRole("USER");
				repo.save(user); // only saving if new
			});
		};
	}

}
