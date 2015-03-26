package ua.nure.bekuzarov.SummaryTask4.db.holder;

import java.sql.Connection;

/**
 * Holds a {@link java.sql.Connection}
 */
public interface ConnectionHolder {

    /**
     * Get a connection.
     * @return connection
     */
    Connection get();

    /**
     * Sets a connection
     * @param connection connection to set
     */
    void set(Connection connection);

    /**
     * Removes a connection
     */
    void remove();

}
