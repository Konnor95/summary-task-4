package ua.nure.bekuzarov.SummaryTask4.filter.security;

import ua.nure.bekuzarov.SummaryTask4.entity.User;
import ua.nure.bekuzarov.SummaryTask4.util.Tuple;

/**
 * Defines whether the current users can access the specified path.
 *
 * @see ua.nure.bekuzarov.SummaryTask4.constant.Actions
 */
public interface AccessConfig {

    /**
     * Defines whether the current users can access the specified path
     *
     * @param path  path to access
     * @param users current users
     * @return {@code true} if can, {@code false} otherwise
     */
    Tuple<Boolean, Boolean> isAllowed(String path, User[] users);

    /**
     * Gets action, where the user will be redirected to in case of denied access.
     *
     * @return action, where the user will be redirected to in case of denied access
     */
    String getRedirect();

}
