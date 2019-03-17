package it.discovery.config;

import it.discovery.repository.BookRepository;
import it.discovery.repository.DBBookRepository;
import it.discovery.repository.XMLBookRepository;
import it.discovery.service.BookService;
import it.discovery.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("application.properties")
public class AppConfig {

    @Bean
    @Qualifier("xml")
    @Profile("test")
    public BookRepository xmlRepository() {
        return new XMLBookRepository();
    }

    @Bean
    @Qualifier("db")
    @Profile("dev")
    public BookRepository dbRepository(Environment env) {
        return new DBBookRepository(env.getProperty("book.server", "localhost"),
                env.getProperty("book.db", "library"));
    }

    @Bean
    public BookService bookService(@Qualifier("db") BookRepository repository) {
        return new BookServiceImpl(repository);
    }
}
