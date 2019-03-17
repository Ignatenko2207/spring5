package it.discovery.service;

import it.discovery.model.Book;
import it.discovery.repository.BookRepository;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;

//@Service
public class BookServiceImpl implements BookService {

	private final BookRepository repository;

    //@Inject
    @Resource
    private List<BookRepository> repositories;

	//@Autowired
	public BookServiceImpl(@NonNull /*@Qualifier("db")*/ BookRepository repository) {
		this.repository = repository;
		System.out.println("Using " + repository.getClass().getSimpleName());
	}

	@Override
    @Async
	public void saveBook(Book book) {
        System.out.println(Thread.currentThread().getName());
		repository.saveBook(book);
	}
	
	@Override
	public Book findBookById(int id) {
		return repository.findBookById(id);
	}

	@Override
	@Async
	public CompletableFuture<List<Book>> findBooks() {
		return CompletableFuture.completedFuture(repository.findBooks());
	}

	public List<BookRepository> getRepositories() {
		return repositories;
	}
}
