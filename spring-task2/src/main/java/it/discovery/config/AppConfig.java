package it.discovery.config;

import it.discovery.logger.FileLogger;
import it.discovery.logger.InMemoryLogger;
import it.discovery.logger.Logger;
import it.discovery.repository.BookRepository;
import it.discovery.repository.DBBookRepository;
import it.discovery.repository.XMLBookRepository;
import it.discovery.service.BookService;
import it.discovery.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
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

    @Configuration
    public class LoggerConfiguration {
        @Bean
        @Order(Ordered.LOWEST_PRECEDENCE)
        public Logger fileLogger() {
            return new FileLogger();
        }

        @Bean
        @Order(Ordered.HIGHEST_PRECEDENCE)
        public Logger inMemoryLogger() {
            return new InMemoryLogger();
        }
    }
}
