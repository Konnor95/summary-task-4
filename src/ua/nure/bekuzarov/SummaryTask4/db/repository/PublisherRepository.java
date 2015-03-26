package ua.nure.bekuzarov.SummaryTask4.db.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.annotation.Repository;
import ua.nure.bekuzarov.SummaryTask4.db.extractor.Extractor;
import ua.nure.bekuzarov.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bekuzarov.SummaryTask4.entity.Publisher;
import ua.nure.bekuzarov.SummaryTask4.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static ua.nure.bekuzarov.SummaryTask4.db.QueryStorage.get;

/**
 * A repository for publishers.
 */
@Repository
public class PublisherRepository extends AbstractRepository<Publisher> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherRepository.class);
    private Extractor<Publisher> extractor = new Extractor<>(Publisher.class);

    /**
     * Creates a new {@code PublisherRepository} object.
     *
     * @param connectionHolder connection holder
     */
    public PublisherRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Publisher add(Publisher publisher) {
        return add(publisher, get("publisher.insert"));
    }

    @Override
    public void update(Publisher publisher) {
    }

    @Override
    public void delete(int id) {
        deleteById(id, get("publisher.delete.by.id"));
    }

    @Override
    public Publisher getById(int id) {
        return getById(id, get("publisher.select.by.id"), extractor);
    }

    @Override
    public List<Publisher> getAll() {
        return getAll(get("publisher.select.all"), extractor);
    }

    @Override
    public List<Publisher> getAll(List<Integer> ids) {
        return getAll(ids, get("publisher.select.all.by.ids"), extractor);
    }

    /**
     * Counts the number of all publishers.
     *
     * @return the number of all publishers
     */
    public int countAll() {
        String sql = get("publisher.count.all");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            return count(ps);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException("Cannot handle sql ['" + sql + "']", e);
        }
    }

    @Override
    protected void prepareForInsert(Publisher publisher, PreparedStatement ps) throws SQLException {
        ps.setInt(1, publisher.getTitle().getId());
    }

    @Override
    protected void prepareForUpdate(Publisher publisher, PreparedStatement ps) throws SQLException {
    }
}
