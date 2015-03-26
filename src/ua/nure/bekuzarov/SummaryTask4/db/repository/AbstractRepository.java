package ua.nure.bekuzarov.SummaryTask4.db.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.db.extractor.Extractor;
import ua.nure.bekuzarov.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bekuzarov.SummaryTask4.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a base functionality for all repositories.
 *
 * @param <T> type repository is responsible for
 * @see ua.nure.bekuzarov.SummaryTask4.annotation.Repository
 */
public abstract class AbstractRepository<T> implements CRUDRepository<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRepository.class);
    protected static final String ERROR_MESSAGE = "Cannot handle sql ['{}']; Message: ";
    private final ConnectionHolder connectionHolder;

    /**
     * Creates a new repository.
     *
     * @param connectionHolder connection holder
     */
    public AbstractRepository(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

    /**
     * Gets an entity by id.
     *
     * @param id        id of the entity
     * @param sql       query to execute. Must have only one prepared parameter
     * @param extractor extractor that can extract the entity of type {@code E}
     * @param <E>       type of the entity. Must inherit the type repository is responsible for
     * @return received entity
     */
    protected <E extends T> E getById(int id, String sql, Extractor<E> extractor) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            List<E> records = executeQuery(ps, extractor);
            return records.isEmpty() ? null : records.get(0);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Gets a connection from connection holder.
     *
     * @return connection from connection holder
     */
    protected Connection getConnection() {
        return connectionHolder.get();
    }

    /**
     * Executes a query
     *
     * @param ps        prepared statement that executes query
     * @param extractor extractor that can extract the entity of type {@code E}
     * @param <E>       type of the result entity
     * @return result entity
     * @throws SQLException if cannot execute query
     */
    protected <E> List<E> executeQuery(PreparedStatement ps, Extractor<E> extractor) throws SQLException {
        List<E> result = new ArrayList<>();
        try (ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                E record = extractor.extract(resultSet);
                result.add(record);
            }
        }
        return result;
    }

    /**
     * Gets all entities
     *
     * @param sql       query to execute. Must have no prepared parameters
     * @param extractor extractor that can extract the entity of type {@code E}
     * @param <E>       type of the entities in result list
     * @return list of entities found
     */
    protected <E extends T> List<E> getAll(String sql, Extractor<E> extractor) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            return executeQuery(ps, extractor);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Gets all entities by their ids
     *
     * @param ids       ids of the entities
     * @param sql       query to execute. Must have as many prepared parameters as {@code ids} list has.
     * @param extractor extractor that can extract the entity of type {@code E}
     * @param <E>       type of the entities in result list
     * @return list of entities found
     */
    protected <E> List<E> getAll(List<Integer> ids, String sql, Extractor<E> extractor) {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        for (int i = 0; i < ids.size(); i++) {
            builder.append("?");
            if (i + 1 != ids.size()) {
                builder.append(", ");
            }
        }
        builder.append(")");
        String sqlString = sql.replace("{in}", builder.toString());
        try (PreparedStatement ps = getConnection().prepareStatement(sqlString)) {
            for (int i = 0; i < ids.size(); i++) {
                ps.setInt(i + 1, ids.get(i));
            }
            return executeQuery(ps, extractor);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sqlString, e);
            throw new DAOException(getMessage(sqlString), e);
        }
    }

    /**
     * Deletes an entity by id.
     *
     * @param id  id of the entity.
     * @param sql query to execute. Must have only one prepared parameter
     */
    protected void deleteById(int id, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Adds a new entity.
     *
     * @param entity entity to add
     * @param sql    query to execute. See {@link #prepareForInsert(Object, java.sql.PreparedStatement)}
     *               to learn more about prepared parameters
     * @return added entity
     */
    protected T add(T entity, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepareForInsert(entity, ps);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            int id = generatedKeys.getInt(1);
            return getById(id);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Adds an new entity and returns the generated id.
     *
     * @param entity entity to add
     * @param sql    query to execute. Must be the same as for {@link #add(Object)} method
     * @return the generated id of the entity
     */
    protected int addOnly(T entity, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepareForInsert(entity, ps);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Adds a list of entities at once.
     *
     * @param entities entities to ass
     * @param sql      query to execute. Must be the same as for {@link #add(Object)} method
     * @return a list of added entities
     */
    protected List<T> addAll(List<T> entities, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (T entity : entities) {
                prepareForInsert(entity, ps);
                ps.addBatch();
            }
            ps.executeBatch();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            List<Integer> ids = new ArrayList<>();
            while (generatedKeys.next()) {
                ids.add(generatedKeys.getInt(1));
            }
            return getAll(ids);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Updates an existing entity
     *
     * @param entity entity to update
     * @param sql    query to execute. See {@link #prepareForUpdate(Object, java.sql.PreparedStatement)}
     *               to learn more about prepared parameters
     */
    protected void update(T entity, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            prepareForUpdate(entity, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Counts a number of entities. Returns {@code 0} if nothing found
     *
     * @param ps prepared statement that executes the query
     * @return a number of entities
     * @throws SQLException if cannot execute query
     */
    protected int count(PreparedStatement ps) throws SQLException {
        try (ResultSet resultSet = ps.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        }
    }

    /**
     * Gets a list of object of the type {@code E}
     *
     * @param ps  prepared statement that executes the query
     * @param c   class of the objects in the list
     * @param <E> type of the objects of the list.
     *            Only built-in types specified in the JDBC specification are supported
     * @return a list of obtained objects
     * @throws SQLException if cannot execute query
     */
    protected <E> List<E> getList(PreparedStatement ps, Class<E> c) throws SQLException {
        List<E> ids = new ArrayList<>();
        try (ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                ids.add(resultSet.getObject(1, c));
            }
        }
        return ids;
    }

    /**
     * Defines whether the entity with the specified id exists
     *
     * @param id  id of the entity
     * @param sql query to execute. Must have only one prepared parameter
     * @return {@code true} if the entity exists, {@code false} otherwise
     */
    protected boolean exists(int id, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return count(ps) >= 1;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Generates a message for {@link ua.nure.bekuzarov.SummaryTask4.exception.DAOException}
     *
     * @param sql query that has not been executed properly
     * @return generated message
     */
    protected String getMessage(String sql) {
        return "Cannot handle sql ['" + sql + "']";
    }

    /**
     * Fills prepared parameters for entity to add.
     * The number of parameters filled by this method must match
     * the number of parameters in the query that adds the entity
     *
     * @param entity entity to add
     * @param ps     prepared statement that sets prepared parameters
     * @throws SQLException if cannot set parameter
     */
    protected abstract void prepareForInsert(T entity, PreparedStatement ps) throws SQLException;

    /**
     * Fills prepared parameters for entity to update.
     * The number of parameters filled by this method must match
     * the number of parameters in query that update the entity
     *
     * @param entity entity to update
     * @param ps     prepared statement that sets prepared parameters
     * @throws SQLException if cannot set parameter
     */
    protected abstract void prepareForUpdate(T entity, PreparedStatement ps) throws SQLException;

}