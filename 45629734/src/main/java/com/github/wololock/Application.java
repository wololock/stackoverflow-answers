package com.github.wololock;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner runner(PersonRepository repository) {
		return (args) -> {
			repository.save(Person.builder().firstName("Joe").lastName("Doe").age(21).build());
			repository.save(Person.builder().firstName("Alan").lastName("Wake").age(22).build());
			repository.save(Person.builder().firstName("Mary").lastName("Henderson").age(21).build());

			System.out.println(repository.findWithCustomQuery("Joe", "Doe", 21));
		};
	}
}
