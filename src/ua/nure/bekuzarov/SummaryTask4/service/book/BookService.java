package ua.nure.bekuzarov.SummaryTask4.service.book;

import ua.nure.bekuzarov.SummaryTask4.annotation.Transactional;
import ua.nure.bekuzarov.SummaryTask4.db.sort.SortOrder;
import ua.nure.bekuzarov.SummaryTask4.entity.Author;
import ua.nure.bekuzarov.SummaryTask4.entity.Book;
import ua.nure.bekuzarov.SummaryTask4.entity.Publisher;
import ua.nure.bekuzarov.SummaryTask4.util.Tuple;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Provides functionality for working with books.
 */
public interface BookService {

    /**
     * Adds a new book. Executes within a transaction.
     *
     * @param book book to add
     * @return added book
     */
    @Transactional
    Book add(Book book);

    /**
     * Updates an existing books. Executes within a transaction.
     *
     * @param book book to update
     */
    @Transactional
    void update(Book book);

    /**
     * Updates an existing books and sets image. Executes within a transaction.
     *
     * @param book book to update
     */
    @Transactional
    void updateWithImage(Book book);

    /**
     * Deletes book. Executes within a transaction.
     *
     * @param book book to delete
     */
    @Transactional
    void delete(Book book);

    /**
     * Defines whether the a with specified id exists.
     *
     * @param id id of the book.
     * @return {@code true} if book exists, {@code false} otherwise.
     */
    boolean exists(int id);

    /**
     * Gets all books.
     *
     * @return list of found books
     */
    List<Book> getAll();

    /**
     * Get all books with specified ids.
     *
     * @param ids ids of the books
     * @return list of found books
     */
    List<Book> getAll(List<Integer> ids);

    /**
     * Gets all books of the author.
     *
     * @param author author of the books
     * @return list of found books
     */
    List<Book> getAll(Author author);

    /**
     * Count the number of all books.
     *
     * @return the number of all books
     */
    int countAll();

    /**
     * Gets images of all books written by author with the specified id.
     *
     * @param author author of the books
     * @return list of found images
     */
    List<String> getImagesByAuthor(Author author);

    /**
     * Gets images of all books published by publisher with the specified id.
     *
     * @param publisher publisher of the books
     * @return list of found images
     */
    List<String> getImagesByPublisher(Publisher publisher);

    /**
     * Gets book by id.
     *
     * @param id id of the book
     * @return found book
     */
    Book getById(int id);

    /**
     * Searches books.
     *
     * @param page         current page
     * @param itemsPerPage number of books per page
     * @param search       search string
     * @param orderBy      order by parameter
     * @param locales      all locales
     * @param locale       current locale
     * @return tuple (first element - found books, second - max number of books if do not
     * take {@code offset} and {@code count} into account
     */
    Tuple<List<Book>, Integer> getAll(int page, int itemsPerPage, String search, String orderBy, Collection<String> locales, String locale);

    /**
     * Gets available sort orders.
     *
     * @return map of available sort orders
     */
    Map<String, SortOrder> getSortOrders();

}
