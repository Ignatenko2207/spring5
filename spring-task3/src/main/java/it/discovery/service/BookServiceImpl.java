package it.discovery.service;

import it.discovery.events.LogEvent;
import it.discovery.model.Book;
import it.discovery.processor.Init;
import it.discovery.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

//@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    //@Autowired
    public BookServiceImpl(@NonNull /*@Qualifier("db")*/ BookRepository repository) {
        this.repository = repository;
        System.out.println("Using " + repository.getClass().getSimpleName());
    }

    @Override
    public void saveBook(Book book) {
        repository.saveBook(book);

        eventPublisher.publishEvent(new LogEvent("bookService",
                "Saved book: " + book));
    }

    @Override
    public Book findBookById(int id) {
        return repository.findBookById(id);
    }

    @Init
    public void initService() {
        System.out.println("Init was done for proxy ");
    }

    @Init
    public void init(ApplicationContext context) {
        System.out.println("Init was done for proxy with context: " + context);
    }

    @Override
    @Async
    public CompletableFuture<List<Book>> findBooks() {
        return CompletableFuture.completedFuture(repository.findBooks());
    }

}
