package dev.rhc.apiuser;

import dev.rhc.apiuser.model.User;
import dev.rhc.apiuser.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.UUID;

@SpringBootApplication
public class ApiuserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiuserApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(UserRepository users) {
		return args -> {
			users.save(new User(UUID.fromString("12345678-1234-5678-1234-567812345678"), "harry", "admin@gmail.com", "pass", null, null, null, "token", true, null));

		};


	}

}


