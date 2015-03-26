package ua.nure.bekuzarov.SummaryTask4.validator;

import java.util.Map;

/**
 * Validates object(s) for invalid data.
 */
public interface Validator {

    /**
     * Returns {@code true} if the validator found errors, {@code false} otherwise.
     * @return {@code true} if the validator found errors, {@code false} otherwise
     */
    boolean hasErrors();

    /**
     * Gets messages of found errors.
     * @return messages of found errors
     */
    Map<String, String> getMessages();

    /**
     * Adds a new message with the specified key.
     * @param key key the message refers to
     * @param message message text
     */
    void put(String key, String message);

}
