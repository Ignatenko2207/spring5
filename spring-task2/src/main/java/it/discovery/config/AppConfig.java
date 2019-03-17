package it.discovery.config;

import it.discovery.events.EventBus;
import it.discovery.logger.FileLogger;
import it.discovery.logger.InMemoryLogger;
import it.discovery.logger.Logger;
import it.discovery.processor.DebugPrintBeanPostProcessor;
import it.discovery.processor.InitBeanPostProcessor;
import it.discovery.repository.BookRepository;
import it.discovery.repository.DBBookRepository;
import it.discovery.repository.XMLBookRepository;
import it.discovery.service.BookService;
import it.discovery.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;

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
        public Logger fileLogger() {
            return new FileLogger();
        }

        @Bean
        @Order(Ordered.HIGHEST_PRECEDENCE)
        public Logger inMemoryLogger() {
            return new InMemoryLogger();
        }

        @Bean
        public EventBus eventBus(List<Logger> loggers) {
            return new EventBus(loggers);
        }
    }

    @Configuration
    public class BeanPostProcessorConfig {
        @Bean
        public BeanPostProcessor debugPrintBeanPostProcessor() {
            return new DebugPrintBeanPostProcessor();
        }

        @Bean
        public InitBeanPostProcessor initBeanPostProcessor() {
            return new InitBeanPostProcessor();
        }
    }
}
