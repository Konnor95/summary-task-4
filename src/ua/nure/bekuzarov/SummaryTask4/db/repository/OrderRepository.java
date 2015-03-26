package ua.nure.bekuzarov.SummaryTask4.db.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.annotation.Repository;
import ua.nure.bekuzarov.SummaryTask4.db.extractor.Extractor;
import ua.nure.bekuzarov.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bekuzarov.SummaryTask4.entity.DetailedOrder;
import ua.nure.bekuzarov.SummaryTask4.entity.Order;
import ua.nure.bekuzarov.SummaryTask4.entity.OrderType;
import ua.nure.bekuzarov.SummaryTask4.exception.DAOException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static ua.nure.bekuzarov.SummaryTask4.db.QueryStorage.get;
import static ua.nure.bekuzarov.SummaryTask4.util.Settings.getFee;

/**
 * A repository for orders.
 */
@Repository
public class OrderRepository extends AbstractRepository<Order> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRepository.class);
    private Extractor<Order> extractor = new Extractor<>(Order.class);
    private Extractor<DetailedOrder> detailedOrderExtractor = new Extractor<>(DetailedOrder.class);

    /**
     * Creates a new {@code OrderRepository} object.
     *
     * @param connectionHolder connection holder
     */
    public OrderRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Order add(Order order) {
        return add(order, get("order.insert"));
    }

    /**
     * Adds a list of orders at once.
     *
     * @param orders orders to add
     * @return added orders
     */
    public List<Order> addAll(List<Order> orders) {
        return addAll(orders, get("order.insert"));
    }

    @Override
    public void update(Order order) {
        update(order, get("order.update"));
    }

    @Override
    public void delete(int id) {
        deleteById(id, get("order.delete.by.id"));
    }

    @Override
    public Order getById(int id) {
        return getById(id, get("order.select.by.id"), extractor);
    }

    /**
     * Gets all orders of specified type by number of reader's subscription .
     *
     * @param subscriptionId number of subscription
     * @param type           order type
     * @return orders found
     */
    public List<DetailedOrder> getAll(int subscriptionId, OrderType type) {
        String sql = get("order.select.by.subscriptionId");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, subscriptionId);
            ps.setInt(2, type.ordinal());
            return executeQuery(ps, detailedOrderExtractor);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    @Override
    public List<Order> getAll() {
        return getAll(get("order.select.all"), extractor);
    }

    /**
     * Gets all orders by ids.
     *
     * @param ids ids of the order
     * @return found orders
     */
    public List<Order> getAll(List<Integer> ids) {
        return getAll(ids, get("order.select.all.by.ids"), extractor);
    }

    /**
     * Gets all order of specified type.
     *
     * @param type order type
     * @return found orders
     */
    public List<Order> getAll(OrderType type) {
        String sql = get("order.select.all.by.type");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, type.ordinal());
            return executeQuery(ps, extractor);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Counts the number of orders, where books are written by the author with specified id.
     *
     * @param authorId id of the author
     * @return the number of orders, where books are written by the author with specified id
     */
    public int countByAuthor(int authorId) {
        String sql = get("order.count.by.author");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, authorId);
            return count(ps);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Counts the number of orders, where books are published by the publisher with specified id.
     *
     * @param publisherId id of the author
     * @return the number of orders, where books are published by the publisher with specified id
     */
    public int countByPublisher(int publisherId) {
        String sql = get("order.count.by.publisher");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, publisherId);
            return count(ps);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Counts the number orders with specified book id.
     *
     * @param bookId id of the book
     * @return the number orders with specified book id
     */
    public int countByBook(int bookId) {
        String sql = get("order.count.by.book");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, bookId);
            return count(ps);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Count the number of orders of specified type.
     *
     * @param type order type
     * @return the number of orders of specified type
     */
    public int countByType(OrderType type) {
        String sql = get("order.count.by.type");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, type.ordinal());
            return count(ps);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Updates overdue orders.
     */
    public void updateOrders() {
        float fee = getFee();
        Date date = new Date(new java.util.Date().getTime());
        String sql = get("order.update.overdue");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setFloat(1, fee);
            ps.setDate(2, date);
            ps.setInt(3, OrderType.CHECKED_OUT.ordinal());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Updates orders made in reading room(s).
     */
    public void updateOrdersInReadingRooms() {
        float fee = getFee();
        String sql = get("order.update.overdue.inReadingRooms");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setFloat(1, fee);
            ps.setInt(2, OrderType.ORDERED.ordinal());
            ps.setInt(3, OrderType.READING_ROOM.ordinal());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    /**
     * Deletes overdue ordered books (orders).
     */
    public void deleteOverdueOrdered() {
        Date date = new Date(new java.util.Date().getTime());
        String sql = get("order.delete.ordered");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setDate(1, date);
            ps.setInt(2, OrderType.ORDERED.ordinal());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    @Override
    protected void prepareForInsert(Order order, PreparedStatement ps) throws SQLException {
        ps.setInt(1, order.getType().ordinal());
        ps.setInt(2, order.getSubscriptionId());
        ps.setInt(3, order.getBookId());
        ps.setDate(4, order.getDueDate());
    }

    @Override
    protected void prepareForUpdate(Order order, PreparedStatement ps) throws SQLException {
        ps.setInt(1, order.getType().ordinal());
        ps.setDate(2, order.getDueDate());
        ps.setInt(3, order.getId());
    }

}
