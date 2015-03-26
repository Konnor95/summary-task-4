package ua.nure.bekuzarov.SummaryTask4.service.author;

import ua.nure.bekuzarov.SummaryTask4.entity.Author;

import java.util.List;

/**
 * Provides functionality for working with authors.
 */
public interface AuthorService {

    /**
     * Adds a new author.
     *
     * @param author author to add.
     * @return added author
     */
    Author add(Author author);

    /**
     * Gets author by id.
     *
     * @param id id of the author
     * @return author object
     */
    Author getById(int id);

    /**
     * Gets all authors.
     *
     * @return list of all authors
     */
    List<Author> getAll();

    /**
     * Gets all author with specified {@code ids}
     *
     * @param ids ids of the authors
     * @return list of the authors found
     */
    List<Author> getAll(List<Integer> ids);

    /**
     * Updates an existing author.
     *
     * @param author author to update.
     */
    void update(Author author);

    /**
     * Deletes author.
     *
     * @param author author to delete.
     */
    void delete(Author author);

    /**
     * Counts the number of all authors.
     *
     * @return the number of all authors
     */
    int countAll();

}
