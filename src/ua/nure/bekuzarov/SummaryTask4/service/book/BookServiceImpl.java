package ua.nure.bekuzarov.SummaryTask4.service.book;

import ua.nure.bekuzarov.SummaryTask4.annotation.Autowired;
import ua.nure.bekuzarov.SummaryTask4.annotation.Service;
import ua.nure.bekuzarov.SummaryTask4.db.repository.BookRepository;
import ua.nure.bekuzarov.SummaryTask4.db.sort.SortOrder;
import ua.nure.bekuzarov.SummaryTask4.entity.Author;
import ua.nure.bekuzarov.SummaryTask4.entity.Book;
import ua.nure.bekuzarov.SummaryTask4.entity.Publisher;
import ua.nure.bekuzarov.SummaryTask4.util.Tuple;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Provides implementation of {@link BookService} interface.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public Book add(Book book) {
        return repository.add(book);
    }

    @Override
    public void update(Book book) {
        repository.update(book);
    }

    @Override
    public void updateWithImage(Book book) {
        repository.updateWithImage(book);
    }

    @Override
    public void delete(Book book) {
        repository.delete(book.getId());
    }

    @Override
    public boolean exists(int id) {
        return repository.exists(id);
    }

    @Override
    public List<Book> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Book> getAll(List<Integer> ids) {
        return repository.getAll(ids);
    }

    @Override
    public List<Book> getAll(Author author) {
        if (author == null) {
            return null;
        }
        return repository.getAll(author.getId());
    }

    @Override
    public int countAll() {
        return repository.countAll();
    }

    @Override
    public List<String> getImagesByAuthor(Author author) {
        return repository.getImagesByAuthor(author.getId());
    }

    @Override
    public List<String> getImagesByPublisher(Publisher publisher) {
        return repository.getImagesByPublisher(publisher.getId());
    }

    @Override
    public Book getById(int id) {
        return repository.getById(id);
    }

    @Override
    public Tuple<List<Book>, Integer> getAll(int page, int itemsPerPage, String search, String orderBy, Collection<String> locales, String locale) {
        return repository.search((page - 1) * itemsPerPage, itemsPerPage, search, orderBy, locales, locale);
    }

    @Override
    public Map<String, SortOrder> getSortOrders() {
        return repository.getSortOrders();
    }

}
