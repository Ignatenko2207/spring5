package it.discovery.config;

import it.discovery.repository.BookRepository;
import it.discovery.repository.DBBookRepository;
import it.discovery.repository.XMLBookRepository;
import it.discovery.service.BookService;
import it.discovery.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@PropertySource("application.properties")
@EnableAsync
//@ComponentScan("it.discovery")
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
    @Lazy
    public BookRepository dbRepository(Environment env) {
        return new DBBookRepository(env.getProperty("book.server", "localhost"),
                env.getProperty("book.db", "library"));
    }

    @Lazy
    @Bean
    public String myBean() {
        return "";
    }

    @Bean
    public BookService bookService(@Qualifier("db") BookRepository repository) {
        return new BookServiceImpl(repository);
    }
}
