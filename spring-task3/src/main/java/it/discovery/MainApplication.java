package it.discovery;

import it.discovery.registry.SpringBeanCustomRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class MainApplication {
	public static void main(String[] args) {
		SpringApplication.run(
				MainApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> System.out.println("My args: " + Arrays.asList(args));
    }

    @Bean
    public SpringBeanCustomRegistry springBeanCustomRegistry() {
        return new SpringBeanCustomRegistry();
    }
}
