package ua.nure.bekuzarov.SummaryTask4.service.user;

import ua.nure.bekuzarov.SummaryTask4.annotation.Transactional;
import ua.nure.bekuzarov.SummaryTask4.entity.*;

import java.util.List;

/**
 * Provides functionality for working with users.
 */
public interface UserService {

    /**
     * Adds a new user.
     *
     * @param user user to add
     * @return added user
     */
    User add(User user);

    /**
     * Updates an existing user.
     *
     * @param user user to update
     */
    void update(User user);

    /**
     * Confirms registered reader. Executes within a transaction
     *
     * @param user reader to confirm
     */
    @Transactional
    void confirm(User user);

    /**
     * Deletes user.
     *
     * @param user user to delete
     */
    void delete(User user);

    /**
     * Gets user by id.
     *
     * @param id id of the user
     * @return found user
     */
    User getById(int id);

    /**
     * Gets reader by id.
     *
     * @param user reader with the specified id
     * @return reader found
     */
    Reader getReaderById(User user);

    /**
     * Gets reader by id
     *
     * @param id id of the reader
     * @return reader found
     */
    Reader getReaderById(int id);

    /**
     * Defines whether the user with specified subscription exists.
     *
     * @param subscriptionId subscription number
     * @return {@code true} if exists, {@code false} otherwise
     */
    boolean readerExists(int subscriptionId);

    /**
     * Gets user by login.
     *
     * @param login login of the user
     * @return user found
     */
    User getByLogin(String login);

    /**
     * Gets user by login.
     *
     * @param login     login of the user
     * @param excludeId id of the user that must be ignored
     * @return user found
     */
    User getByLogin(String login, int excludeId);

    /**
     * Gets user by email.
     *
     * @param email     email of the user
     * @param excludeId id of the user that must be ignored
     * @return user found
     */
    User getByEmail(String email, int excludeId);

    /**
     * Gets users of specified rol.
     *
     * @param role user role
     * @return list of found users
     */
    List<User> getByRole(UserRole role);

    /**
     * Counts the number of users of specified role.
     *
     * @param role user role
     * @return the number of users of specified role
     */
    int countByRole(UserRole role);

    /**
     * Gets all readers.
     *
     * @return list of found readers
     */
    List<Reader> getAllReaders();

    /**
     * Updates subscriptions of readers. Executes within a transaction.
     */
    @Transactional
    void updateSubscriptions();

    /**
     * Updates subscription of {@code reader}
     *
     * @param reader reader whose subscription need to be updated
     */
    @Transactional
    void updateSubscription(Reader reader);

    /**
     * Selects all books that user has not returned in time.
     *
     * @return list of all books that user has not returned in time
     */
    List<UserBook> getBooks();

}
