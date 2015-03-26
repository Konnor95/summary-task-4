package ua.nure.bekuzarov.SummaryTask4.db.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.annotation.Repository;
import ua.nure.bekuzarov.SummaryTask4.db.QueryStorage;
import ua.nure.bekuzarov.SummaryTask4.db.extractor.Extractor;
import ua.nure.bekuzarov.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bekuzarov.SummaryTask4.entity.Author;
import ua.nure.bekuzarov.SummaryTask4.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static ua.nure.bekuzarov.SummaryTask4.db.QueryStorage.get;

/**
 * A repository for authors.
 */
@Repository
public class AuthorRepository extends AbstractRepository<Author> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorRepository.class);
    private Extractor<Author> authorExtractor = new Extractor<>(Author.class);

    /**
     * Creates a new {@code AuthorRepository} object.
     *
     * @param connectionHolder connection holder
     */
    public AuthorRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Author add(Author author) {
        return add(author, QueryStorage.get("author.insert"));
    }

    @Override
    public void update(Author author) {
        update(author, QueryStorage.get("author.update"));
    }

    @Override
    public void delete(int id) {
        deleteById(id, QueryStorage.get("author.delete.by.id"));
    }

    @Override
    public Author getById(int id) {
        return getById(id, QueryStorage.get("author.select.by.id"), authorExtractor);
    }

    @Override
    public List<Author> getAll() {
        return getAll(QueryStorage.get("author.select.all"), authorExtractor);
    }

    @Override
    public List<Author> getAll(List<Integer> ids) {
        return getAll(ids, QueryStorage.get("author.select.all.by.ids"), authorExtractor);
    }

    /**
     * Counts the number of all authors.
     *
     * @return the number of all authors
     */
    public int countAll() {
        String sql = get("author.count.all");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            return count(ps);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException("Cannot handle sql ['" + sql + "']", e);
        }
    }

    @Override
    protected void prepareForInsert(Author author, PreparedStatement ps) throws SQLException {
        ps.setInt(1, author.getName().getId());
        ps.setInt(2, author.getDescription().getId());
    }

    @Override
    protected void prepareForUpdate(Author author, PreparedStatement ps) throws SQLException {
        ps.setString(1, author.getImage());
        ps.setInt(2, author.getId());
    }

}
