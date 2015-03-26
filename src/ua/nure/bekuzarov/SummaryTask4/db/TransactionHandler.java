package ua.nure.bekuzarov.SummaryTask4.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.annotation.Transactional;
import ua.nure.bekuzarov.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bekuzarov.SummaryTask4.db.manager.ConnectionManager;
import ua.nure.bekuzarov.SummaryTask4.exception.DAOException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Provides functionality for transaction handling.
 * Invokes withing a transaction only methods annotated
 * with {@link Transactional}
 * Throws {@link DAOException}
 * if an exception was thrown in invoked method or while managing connection.
 */
public class TransactionHandler implements InvocationHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionHandler.class);
    private static final String CANNOT_INVOKE = "Cannot invoke";
    private static final String CANNOT_COMMIT = "Cannot commit";
    private static final String CANNOT_CLOSE = "Cannot close connection";
    private static final String CANNOT_ROLLBACK = "Cannot rollback";
    private final ConnectionManager connectionManager;
    private final ConnectionHolder connectionHolder;
    private final Object serviceToInvoke;

    /**
     * Creates a new transaction handler.
     *
     * @param manager         connection manager
     * @param holder          connection holder
     * @param serviceToInvoke service to invoke
     */
    public TransactionHandler(ConnectionManager manager, ConnectionHolder holder, Object serviceToInvoke) {
        this.connectionManager = manager;
        this.serviceToInvoke = serviceToInvoke;
        this.connectionHolder = holder;
    }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            if (method.isAnnotationPresent(Transactional.class)) {
                return invokeWithTransaction(method, args);
            }
            return invokeWithoutTransaction(method, args);

        }

    private Object invokeWithoutTransaction(Method method, Object[] args) {
        LOGGER.debug("Invoking without transaction");
        Connection connection = connectionManager.getConnection();
        connectionHolder.set(connection);
        try {
            connection.setAutoCommit(true);
            return method.invoke(serviceToInvoke, args);
        } catch (Exception e) {
            LOGGER.warn(CANNOT_INVOKE, e);
            throw new DAOException(CANNOT_INVOKE, e);
        } finally {
            closeConnection(connection);
            connectionHolder.remove();
        }
    }

    private Object invokeWithTransaction(Method method, Object[] args) {
        LOGGER.debug("Invoking with transaction");
        Connection connection = connectionManager.getConnection();
        connectionHolder.set(connection);
        try {
            Object result;
            connection.setAutoCommit(false);
            try {
                result = method.invoke(serviceToInvoke, args);

            } catch (Exception e) {
                LOGGER.warn(CANNOT_INVOKE, e);
                throw new DAOException(CANNOT_INVOKE, e);
            }
            connection.commit();
            return result;
        } catch (Exception e) {
            rollback(connection);
            LOGGER.warn(CANNOT_COMMIT, e);
            throw new DAOException(CANNOT_COMMIT, e);
        } finally {
            closeConnection(connection);
            connectionHolder.remove();
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(CANNOT_CLOSE, e.getMessage());
            throw new DAOException(CANNOT_CLOSE, e);
        }
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error(CANNOT_ROLLBACK, e);
            throw new DAOException(CANNOT_ROLLBACK, e);
        }
    }

}
