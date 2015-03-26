package ua.nure.bekuzarov.SummaryTask4.db.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.annotation.Repository;
import ua.nure.bekuzarov.SummaryTask4.db.extractor.Extractor;
import ua.nure.bekuzarov.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bekuzarov.SummaryTask4.db.sort.BasicSortOrder;
import ua.nure.bekuzarov.SummaryTask4.db.sort.SortOrder;
import ua.nure.bekuzarov.SummaryTask4.db.sort.TranslationSortOrder;
import ua.nure.bekuzarov.SummaryTask4.entity.Author;
import ua.nure.bekuzarov.SummaryTask4.entity.Book;
import ua.nure.bekuzarov.SummaryTask4.entity.BookInfo;
import ua.nure.bekuzarov.SummaryTask4.entity.Publisher;
import ua.nure.bekuzarov.SummaryTask4.exception.DAOException;
import ua.nure.bekuzarov.SummaryTask4.util.Tuple;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static ua.nure.bekuzarov.SummaryTask4.db.QueryStorage.get;

/**
 * A repository for books.
 */
@Repository
public class BookRepository extends AbstractRepository<Book> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookRepository.class);
    private Extractor<Book> bookExtractor = new Extractor<>(Book.class);
    private Extractor<BookInfo> bookInfoExtractor = new Extractor<>(BookInfo.class);
    private Map<String, SortOrder> sortOrders;

    /**
     * Creates a new {@code BookRepository} object.
     *
     * @param connectionHolder connection holder
     */
    public BookRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
        sortOrders = new HashMap<>();
        sortOrders.put("default", new BasicSortOrder("book", "id"));
        sortOrders.put("year", new BasicSortOrder("book", "year"));
        sortOrders.put("title", new TranslationSortOrder("book_title"));
        sortOrders.put("publisher", new TranslationSortOrder("publisher_title"));
    }

    @Override
    public Book add(Book book) {
        int newBook = addOnly(book, get("book.insert"));
        book.setId(newBook);
        addAuthors(book);
        return getById(newBook);
    }

    @Override
    public void update(Book book) {
        update(book, get("book.update"));
        updateAuthors(book);
    }

    /**
     * Updates a book that and sets its image.
     *
     * @param book book to update
     */
    public void updateWithImage(Book book) {
        String sql = get("book.updateWithImage");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            prepareForUpdateWithImage(book, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    @Override
    public void delete(int id) {
        deleteById(id, get("book.delete.by.id"));
    }

    @Override
    public Book getById(int id) {
        return getById(id, get("book.select.by.id"), bookExtractor);
    }

    @Override
    public List<Book> getAll() {
        return getAll(get("book.select.all"), bookExtractor);
    }

    @Override
    public List<Book> getAll(List<Integer> ids) {
        return getAll(ids, get("book.select.all.by.ids"), bookExtractor);
    }

    /**
     * Gets all info about the books with specified ids
     *
     * @param ids ids of the books
     * @return list of book info
     */
    public List<BookInfo> getAllInfo(List<Integer> ids) {
        return getAll(ids, get("book.select.all.info.by.ids"), bookInfoExtractor);
    }

    /**
     * Gets all books of the author.
     *
     * @param authorId id of the author
     * @return list of found books
     */
    public List<Book> getAll(int authorId) {
        String sql = get("book.select.all.by.author.id");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, authorId);
            return executeQuery(ps, bookExtractor);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Searches books.
     *
     * @param offset  number of books to skip
     * @param count   number of books to return
     * @param search  search string
     * @param orderBy order by parameter
     * @param locales all locales
     * @param locale  current locale
     * @return tuple (first element - found books, second - max number of books if do not
     * take {@code offset} and {@code count} into account
     */
    public Tuple<List<Book>, Integer> search(int offset, int count, String search, String orderBy, Collection<String> locales, String locale) {
        SortOrder order = sortOrders.get(orderBy);
        if (order == null) {
            order = sortOrders.get("default");
        }
        String orderByValue = order.getValue(locale);
        return search == null ? search(offset, count, orderByValue)
                : searchByName(offset, count, search, orderByValue, locales);
    }

    /**
     * Gets images of all books written by author with the specified id.
     *
     * @param authorId id of the author
     * @return list of found images
     */
    public List<String> getImagesByAuthor(int authorId) {
        String sql = get("book.select.images.by.authorId");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, authorId);
            return getList(ps, String.class);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Gets images of all books published by publisher with the specified id.
     *
     * @param publisherId id of the publisher
     * @return list of found images
     */
    public List<String> getImagesByPublisher(int publisherId) {
        String sql = get("book.select.images.by.publisherId");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, publisherId);
            return getList(ps, String.class);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Gets available sort orders.
     *
     * @return map of available sort orders
     */
    public Map<String, SortOrder> getSortOrders() {
        return sortOrders;
    }

    /**
     * Updates the number of books with specified id in stock.
     *
     * @param bookId   id of the books
     * @param quantity new number of books in stock
     */
    public void updateStock(int bookId, int quantity) {
        String sql = get("book.updateStock");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, bookId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Defines whether the a with specified id exists.
     *
     * @param id id of the book.
     * @return {@code true} if book exists, {@code false} otherwise.
     */
    public boolean exists(int id) {
        return exists(id, get("book.count.by.id"));
    }

    /**
     * Count the number of all books.
     *
     * @return the number of all books
     */
    public int countAll() {
        String sql = get("book.count.all");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            return count(ps);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    @Override
    protected void prepareForInsert(Book book, PreparedStatement ps) throws SQLException {
        ps.setInt(1, book.getTitle().getId());
        ps.setInt(2, book.getDescription().getId());
        ps.setInt(3, book.getAmount());
        ps.setInt(4, book.getAmount());
        ps.setInt(5, book.getPublisher().getId());
        ps.setInt(6, book.getYear());
        ps.setInt(7, book.getPages());
    }

    @Override
    protected void prepareForUpdate(Book book, PreparedStatement ps) throws SQLException {
        Publisher p = book.getPublisher();
        ps.setInt(1, book.getAmount());
        ps.setInt(2, book.getInStock());
        if (p == null) {
            ps.setObject(3, null);
        } else {
            ps.setInt(3, p.getId());
        }
        ps.setInt(4, book.getYear());
        ps.setInt(5, book.getPages());
        ps.setInt(6, book.getId());
    }

    protected void prepareForUpdateWithImage(Book book, PreparedStatement ps) throws SQLException {
        Publisher p = book.getPublisher();
        ps.setInt(1, book.getAmount());
        ps.setInt(2, book.getInStock());
        if (p == null) {
            ps.setObject(3, null);
        } else {
            ps.setInt(3, p.getId());
        }
        ps.setInt(4, book.getYear());
        ps.setInt(5, book.getPages());
        ps.setString(6, book.getImage());
        ps.setInt(7, book.getId());
    }

    private void addAuthors(Book book) {
        String sql = get("book.insertAuthors");
        List<Author> authors = book.getAuthors();
        if (authors == null) {
            return;
        }
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            for (int i = 0; i < book.getAuthors().size(); i++) {
                ps.setInt(1, book.getId());
                ps.setInt(2, authors.get(i).getId());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    private void updateAuthors(Book book) {
        deleteById(book.getId(), get("book.deleteBookAuthors.by.id"));
        addAuthors(book);
    }


    private Tuple<List<Book>, Integer> search(int offset, int count, String orderBy) {
        String sql = get("book.search");
        sql = sql.replace("{order}", orderBy);
        List<Book> books = getBooks(sql, offset, count);
        sql = get("book.search.count");
        return executeCount(books, sql, count);
    }

    private Tuple<List<Book>, Integer> searchByName(int offset, int count, String searchString, String orderBy, Collection<String> locales) {
        String sql = get("book.search.by.name");
        String search = searchString.replace("'", "''");
        sql = sql.replace("{order}", orderBy);
        StringBuilder builder = new StringBuilder();
        List<String> localesList = new ArrayList<>();
        localesList.addAll(locales);
        for (int i = 0; i < localesList.size() - 1; i++) {
            builder.append("book_title_").append(localesList.get(i)).append(" LIKE '%").append(search).append("%' OR ");
        }
        builder.append("book_title_").append(localesList.get(localesList.size() - 1)).append(" LIKE '%").append(search).append("%' ");
        sql = sql.replace("{where}", builder.toString());
        List<Book> books = getBooks(sql, offset, count);
        sql = get("book.search.by.name.count");
        sql = sql.replace("{where}", builder.toString());
        return executeCount(books, sql, count);
    }

    private List<Book> getBooks(final String sql, int offset, int count) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, offset);
            ps.setInt(2, count);
            return executeQuery(ps, bookExtractor);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    private Tuple<List<Book>, Integer> executeCount(List<Book> books, final String sql, int count) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int size = rs.getInt(1);
                return new Tuple<>(books, (int) Math.ceil(size * 1.0 / count));
            } else {
                return new Tuple<>(books, 0);
            }
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }


}
