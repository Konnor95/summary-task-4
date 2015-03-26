package ua.nure.bekuzarov.SummaryTask4.service.order;

import ua.nure.bekuzarov.SummaryTask4.annotation.Transactional;
import ua.nure.bekuzarov.SummaryTask4.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Provides functionality for working with orders.
 */
public interface OrderService {

    /**
     * Counts the number of ordered books written by the {@code author}.
     *
     * @param author author of the books
     * @return number of ordered books written by the {@code author}
     */
    int countByAuthor(Author author);

    /**
     * Counts the number of ordered books published by the {@code publisher}.
     *
     * @param publisher publisher of the books
     * @return publisher of ordered books written by the {@code publisher}
     */
    int countByPublisher(Publisher publisher);

    /**
     * Counts the number of times the {@code book} was ordered.
     *
     * @param book ordered book
     * @return the number of times the {@code book} was ordered
     */
    int countByBook(Book book);

    /**
     * Counts the number of orders of specified {@code type}
     *
     * @param type type of the orders
     * @return the number of orders of specified {@code type}
     */
    int countByType(OrderType type);

    /**
     * Adds new order.
     *
     * @param order order to add
     * @return added order
     */
    Order add(Order order);

    /**
     * Adds new orders of the {@code reader}. Executes within a transaction.
     *
     * @param reader       the reader who ordered the books
     * @param orderedBooks ids and the number of ordered books
     * @return list of created object.
     */
    @Transactional
    List<Order> add(Reader reader, Map<Integer, Integer> orderedBooks);

    /**
     * Gets detail information about the orders made by the {@code reader}.
     *
     * @param reader readers who made orders.
     * @return a list of orders of the {@code reader}
     */
    List<DetailedOrder> getOrdered(Reader reader);

    /**
     * Gets detail information about the books checked out by the {@code reader}.
     *
     * @param reader readers who checked out the books
     * @return detail information about the books checked out by the {@code reader}
     */
    List<DetailedOrder> getCheckedOut(Reader reader);

    /**
     * Updates an existing order.
     *
     * @param order order to update.
     */
    void update(Order order);

    /**
     * Updates all orders by setting fees to overdue orders.
     * Executes within a transaction.
     */
    @Transactional
    void updateOrders();

    /**
     * Gets order by id.
     *
     * @param id id of the order
     * @return order found
     */
    Order getById(int id);

    /**
     * Gets all orders of the specified {@code type}.
     *
     * @param type type of the orders
     * @return all orders of the specified {@code type}
     */
    List<Order> getAll(OrderType type);

}
