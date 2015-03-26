package ua.nure.bekuzarov.SummaryTask4.db.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.annotation.Repository;
import ua.nure.bekuzarov.SummaryTask4.db.extractor.Extractor;
import ua.nure.bekuzarov.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bekuzarov.SummaryTask4.entity.*;
import ua.nure.bekuzarov.SummaryTask4.exception.DAOException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static ua.nure.bekuzarov.SummaryTask4.db.QueryStorage.get;

/**
 * A repository for users.
 */
@Repository
public class UserRepository extends AbstractRepository<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);
    private Extractor<User> extractor = new Extractor<>(User.class);
    private Extractor<Reader> readerExtractor = new Extractor<>(Reader.class);
    private Extractor<UserBook> userBookExtractor = new Extractor<>(UserBook.class);

    /**
     * Creates a new {@code UserRepository} object.
     *
     * @param connectionHolder connection holder
     */
    public UserRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public User add(User user) {
        return add(user, get("user.insert"));
    }

    @Override
    public void update(User user) {
        update(user, get("user.update"));
    }

    @Override
    public void delete(int id) {
        deleteById(id, get("user.delete"));
    }

    @Override
    public User getById(int id) {
        return getById(id, get("user.select.by.id"), extractor);
    }

    /**
     * Gets reader by id.
     *
     * @param id id of the reader
     * @return found reader
     */
    public Reader getReaderById(int id) {
        return getById(id, get("user.select.reader.by.id"), readerExtractor);
    }

    @Override
    public List<User> getAll() {
        return getAll(get("user.select.all"), extractor);
    }

    @Override
    public List<User> getAll(List<Integer> ids) {
        return getAll(ids, get("user.select.all.by.ids"), extractor);
    }

    /**
     * Gets user by login.
     *
     * @param login     login of the user
     * @param excludeId id of the user that must be ignored
     * @return user found
     */
    public User getByLogin(String login, int excludeId) {
        String sql = get("user.select.by.login");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setInt(2, excludeId);
            List<User> users = executeQuery(ps, extractor);
            return users.isEmpty() ? null : users.get(0);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Gets user by email.
     *
     * @param email     email of the user
     * @param excludeId id of the user that must be ignored
     * @return user found
     */
    public User getByEmail(String email, int excludeId) {
        String sql = get("user.select.by.email");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setInt(2, excludeId);
            List<User> users = executeQuery(ps, extractor);
            return users.isEmpty() ? null : users.get(0);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Gets users of specified rol.
     *
     * @param role user role
     * @return list of found users
     */
    public List<User> getByRole(UserRole role) {
        String sql = get("user.select.by.role");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, role.ordinal());
            return executeQuery(ps, extractor);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Gets all readers.
     *
     * @return list of found readers
     */
    public List<Reader> getAllReaders() {
        String sql = get("user.select.all.readers");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, UserRole.USER.ordinal());
            return executeQuery(ps, readerExtractor);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Counts the number of users of specified role.
     *
     * @param role user role
     * @return the number of users of specified role
     */
    public int countByRole(UserRole role) {
        String sql = get("user.count.all.by.role");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, role.ordinal());
            return count(ps);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Updates subscriptions of readers.
     */
    public void updateSubscriptions() {
        String sql = get("user.update.overdue");
        Date date = new Date(new java.util.Date().getTime());
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, UserRole.OVERDUE.ordinal());
            ps.setDate(2, date);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Get books by order type.
     *
     * @return list of overdue books
     */
    public List<UserBook> getOrders() {
        String sql = get("user.select.all.books.by.type");
        return getAll(sql, userBookExtractor);
    }

    @Override
    protected void prepareForInsert(User user, PreparedStatement ps) throws SQLException {
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getLogin());
        ps.setString(5, user.getPassword());
        ps.setInt(6, user.getRole().ordinal());
    }

    @Override
    protected void prepareForUpdate(User user, PreparedStatement ps) throws SQLException {
        prepareForInsert(user, ps);
        ps.setInt(7, user.getId());
    }

}
