package ua.nure.bekuzarov.SummaryTask4.db.repository;

import java.util.List;

/**
 * Responsible for transferring data to and from database.
 * @param <T> type the repository is responsible for
 */
public interface CRUDRepository<T> {

    /**
     * Adds an new entity.
     * @param entity entity to add
     * @return added entity
     */
    T add(T entity);

    /**
     * Updates an existing entity
     * @param entity entity to update
     */
    void update(T entity);

    /**
     * Deletes entity be id
     * @param id id of the entity
     */
    void delete(int id);

    /**
     * Gets an entity by id.
     * @param id id of the entity
     * @return an entity with the specified id
     */
    T getById(int id);

    /**
     * Gets all entities
     * @return all entities
     */
    List<T> getAll();

    /**
     * Gets all entities with the specified ids
     * @param ids ids of the entities
     * @return all entities with the specified ids
     */
    List<T> getAll(List<Integer> ids);

}
