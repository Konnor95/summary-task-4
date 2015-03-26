package ua.nure.bekuzarov.SummaryTask4.service.order;

import ua.nure.bekuzarov.SummaryTask4.annotation.Autowired;
import ua.nure.bekuzarov.SummaryTask4.annotation.Service;
import ua.nure.bekuzarov.SummaryTask4.db.repository.BookRepository;
import ua.nure.bekuzarov.SummaryTask4.db.repository.OrderRepository;
import ua.nure.bekuzarov.SummaryTask4.entity.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static ua.nure.bekuzarov.SummaryTask4.util.Settings.getOrderDuration;

/**
 * Provides implementation of {@link OrderService} interface.
 */
@Service
public final class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public int countByAuthor(Author author) {
        return repository.countByAuthor(author.getId());
    }

    @Override
    public int countByPublisher(Publisher publisher) {
        return repository.countByPublisher(publisher.getId());
    }

    @Override
    public int countByBook(Book book) {
        return repository.countByBook(book.getId());
    }

    @Override
    public int countByType(OrderType type) {
        return repository.countByType(type);
    }

    @Override
    public Order add(Order order) {
        return repository.add(order);
    }

    @Override
    public List<Order> add(Reader reader, Map<Integer, Integer> orderedBooks) {
        List<Order> orders = new ArrayList<>();
        List<Integer> ids = new ArrayList<>(orderedBooks.keySet());
        List<BookInfo> bookInfoList = bookRepository.getAllInfo(ids);
        for (BookInfo info : bookInfoList) {
            int amount = orderedBooks.get(info.getId());
            if (info.getInStock() < amount) {
                return null;
            }
            bookRepository.updateStock(info.getId(), info.getInStock() - amount);
        }
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date());
        c.add(Calendar.DATE, getOrderDuration());
        Date dueDate = new Date(c.getTime().getTime());
        for (Integer bookId : ids) {
            for (int i = 0; i < orderedBooks.get(bookId); i++) {
                Order order = new Order();
                order.setBookId(bookId);
                order.setSubscriptionId(reader.getSubscription().getId());
                order.setDueDate(dueDate);
                order.setType(OrderType.ORDERED);
                orders.add(order);
            }
        }

        return repository.addAll(orders);
    }

    @Override
    public List<DetailedOrder> getOrdered(Reader reader) {
        return repository.getAll(reader.getSubscription().getId(), OrderType.ORDERED);
    }

    @Override
    public List<DetailedOrder> getCheckedOut(Reader reader) {
        return repository.getAll(reader.getSubscription().getId(), OrderType.CHECKED_OUT);
    }

    @Override
    public void update(Order order) {
        repository.update(order);
    }

    @Override
    public void updateOrders() {
        repository.updateOrders();
        repository.deleteOverdueOrdered();
        repository.updateOrdersInReadingRooms();
    }

    @Override
    public Order getById(int id) {
        return repository.getById(id);
    }

    @Override
    public List<Order> getAll(OrderType type) {
        return repository.getAll(type);
    }

}
