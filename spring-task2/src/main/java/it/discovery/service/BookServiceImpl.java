package it.discovery.service;

import it.discovery.model.Book;
import it.discovery.repository.BookRepository;
import org.springframework.lang.NonNull;

import javax.annotation.Resource;
import java.util.List;

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
	public void saveBook(Book book) {
		repository.saveBook(book);
	}
	
	@Override
	public Book findBookById(int id) {
		return repository.findBookById(id);
	}

	@Override
	public List<Book> findBooks() {
		return repository.findBooks();
	}

	public List<BookRepository> getRepositories() {
		return repositories;
	}
}
