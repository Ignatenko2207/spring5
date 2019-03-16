package it.discovery.config;

import it.discovery.repository.BookRepository;
import it.discovery.repository.DBBookRepository;
import it.discovery.repository.XMLBookRepository;
import it.discovery.service.BookService;
import it.discovery.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
public class AppConfig {

    @Bean
    @Qualifier("xml")
    public BookRepository xmlRepository() {
        return new XMLBookRepository();
    }

    @Bean
    @Qualifier("db")
    public BookRepository dbRepository(@Value("${book.server}") String server, @Value("${book.db}") String db) {
        return new DBBookRepository(server, db);
    }

    @Bean
    public BookService bookService(@Qualifier("db") BookRepository repository) {
        return new BookServiceImpl(repository);
    }
}
