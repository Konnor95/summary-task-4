package ua.nure.bekuzarov.SummaryTask4.exception;

/**
 * {@code ExtractionException} is thrown then the {@code Extractor}
 * cannot extract the object.
 *
 * @author Dmitry Bekuzarov
 * @see ua.nure.bekuzarov.SummaryTask4.db.extractor.Extractor
 */
public class ExtractionException extends RuntimeException {

    /**
     * Creates a new {@code ExtractionException} object with a specified message and cause.
     *
     * @param message message of the exception
     * @param cause   cause of the exception
     */
    public ExtractionException(String message, Throwable cause) {
        super(message, cause);
    }

}
