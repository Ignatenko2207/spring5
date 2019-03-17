package it.discovery.repository;

import it.discovery.model.Book;
import it.discovery.processor.Init;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles database-related book operations
 *
 * @author morenets
 */
//@Repository
//@Qualifier("db")
@RequiredArgsConstructor
public class DBBookRepository implements BookRepository {
    private final Map<Integer, Book> books = new HashMap<>();

    private int counter = 0;

    private final String server; // = "localhost";

    private final String db; // = "library";

    @PostConstruct
    public void init() {
        System.out.println("Started db repository with server:" + server + " and database: " + db);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Shutting down repository ... ");
    }

    @Init
    public void initService() {
        System.out.println("Init was done");
    }

    @Init
    public void init(ApplicationContext context) {
        System.out.println("Init was done with context: " + context);
    }

    @Override
    public void saveBook(Book book) {
        if (book.getId() == 0) {
            counter++;
            book.setId(counter);
        }

        books.put(book.getId(), book);

        System.out.println("Saved book " + book);
    }

    @Override
    public Book findBookById(int id) {
        return books.get(id);
    }

    @Override
    public List<Book> findBooks() {
        return new ArrayList<>(books.values());
    }

}
