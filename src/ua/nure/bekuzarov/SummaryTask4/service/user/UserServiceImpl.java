package ua.nure.bekuzarov.SummaryTask4.service.user;

import ua.nure.bekuzarov.SummaryTask4.annotation.Autowired;
import ua.nure.bekuzarov.SummaryTask4.annotation.Service;
import ua.nure.bekuzarov.SummaryTask4.db.repository.SubscriptionRepository;
import ua.nure.bekuzarov.SummaryTask4.db.repository.UserRepository;
import ua.nure.bekuzarov.SummaryTask4.entity.*;
import ua.nure.bekuzarov.SummaryTask4.util.Settings;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Provides implementation of {@link UserService} interface.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public User add(User user) {
        return userRepository.add(user);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public void confirm(User user) {
        user.setRole(UserRole.USER);
        userRepository.update(user);
        Subscription subscription = new Subscription();
        subscription.setUserId(user.getId());
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, Settings.getSubscriptionDuration());
        Date date = c.getTime();
        subscription.setExpirationDate(new java.sql.Date(date.getTime()));
        subscriptionRepository.add(subscription);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user.getId());
    }

    @Override
    public User getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public Reader getReaderById(User user) {
        if (user == null) {
            return null;
        }
        return getReaderById(user.getId());
    }

    @Override
    public Reader getReaderById(int id) {
        return userRepository.getReaderById(id);
    }

    @Override
    public boolean readerExists(int subscriptionId) {
        return subscriptionRepository.exists(subscriptionId);
    }

    @Override
    public User getByLogin(String login) {
        return getByLogin(login, 0);
    }

    @Override
    public User getByLogin(String login, int excludeId) {
        return userRepository.getByLogin(login, excludeId);
    }

    @Override
    public User getByEmail(String email, int excludeId) {
        return userRepository.getByEmail(email, excludeId);
    }

    @Override
    public List<User> getByRole(UserRole role) {
        return userRepository.getByRole(role);
    }

    @Override
    public int countByRole(UserRole role) {
        return userRepository.countByRole(role);
    }

    @Override
    public List<Reader> getAllReaders() {
        return userRepository.getAllReaders();
    }

    @Override
    public void updateSubscriptions() {
        userRepository.updateSubscriptions();
    }

    @Override
    public void updateSubscription(Reader reader) {
        reader.setRole(UserRole.USER);
        userRepository.update(reader);
        subscriptionRepository.update(reader.getSubscription());
    }

    @Override
    public List<UserBook> getBooks() {
        return userRepository.getOrders();
    }

}