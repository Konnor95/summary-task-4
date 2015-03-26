package ua.nure.bekuzarov.SummaryTask4.entity;

/**
 * Defines roles of the users.
 *
 * @author Dmitry Bekuzarov
 */
public enum UserRole {

    /**
     * User is registered reader but his subscription has not been confirmed yet.
     */
    NOT_CONFIRMED,
    /**
     * A registered reader.
     */
    USER,
    /**
     * A banned reader.
     */
    BANNED,
    /**
     * A librarian.
     */
    LIBRARIAN,
    /**
     * An administrator.
     */
    ADMIN,
    /**
     * A reader whose subscription is overdue
     */
    OVERDUE;

    /**
     * Defines {@code UserRole} from the its ordinal
     * @param roleId role ordinal
     * @return {@code UserRole} object
     * @throws IllegalArgumentException if the ordinal is not valid
     */
    public static UserRole define(int roleId) {
        UserRole[] roles = values();
        if (roleId >= roles.length || roleId < 0) {
            throw new IllegalArgumentException("Invalid role id");
        }
        return roles[roleId];
    }

}
