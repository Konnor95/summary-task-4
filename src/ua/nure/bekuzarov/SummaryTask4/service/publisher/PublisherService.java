package ua.nure.bekuzarov.SummaryTask4.service.publisher;


import ua.nure.bekuzarov.SummaryTask4.entity.Publisher;

import java.util.List;

/**
 * Provides functionality for working with publishers.
 */
public interface PublisherService {

    /**
     * Adds a new publisher.
     *
     * @param publisher publisher to add
     * @return added publisher
     */
    Publisher add(Publisher publisher);

    /**
     * Gets publisher by id.
     *
     * @param id id of the publisher
     * @return found publisher
     */
    Publisher getById(int id);

    /**
     * Gets all publishers.
     *
     * @return list of all publishers
     */
    List<Publisher> getAll();

    /**
     * Updates an existing publisher.
     *
     * @param publisher publisher to update.
     */
    void update(Publisher publisher);

    /**
     * Deletes publisher.
     *
     * @param publisher publisher to delete.
     */
    void delete(Publisher publisher);

    /**
     * Counts the number of all publishers.
     *
     * @return the number of all publishers
     */
    int countAll();

}
