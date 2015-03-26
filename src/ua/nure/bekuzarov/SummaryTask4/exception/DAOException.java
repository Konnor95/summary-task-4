package ua.nure.bekuzarov.SummaryTask4.exception;

/**
 * {@code DAOException} is thrown when the error occurred
 * while data accessing. Is thrown in repositories.
 *
 * @author Dmitry Bekuzarov
 * @see ua.nure.bekuzarov.SummaryTask4.annotation.Repository
 * @see ua.nure.bekuzarov.SummaryTask4.db.repository.CRUDRepository
 */
public class DAOException extends RuntimeException {

    /**
     * Creates a new {@code DAOException} object with a specified message and cause.
     *
     * @param message message of the exception
     * @param cause   cause of the exception
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
