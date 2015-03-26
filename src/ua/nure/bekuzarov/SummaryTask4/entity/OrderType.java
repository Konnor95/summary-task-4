package ua.nure.bekuzarov.SummaryTask4.entity;

/**
 * Type of the order of the book.
 *
 * @author Dmitry Bekuzarov
 */
public enum OrderType {

    /**
     * Book is ordered.
     */
    ORDERED,
    /**
     * Book is checked out.
     */
    CHECKED_OUT,
    /**
     * Book is returned to library.
     */
    COMPLETED,
    /**
     * Book is in the reading room(s).
     */
    READING_ROOM;

    /**
     * Defines {@code OrderType} from the its ordinal
     * @param type type ordinal
     * @return {@code OrderType} object
     * @throws IllegalArgumentException if the ordinal is not valid
     */
    public static OrderType define(int type) {
        OrderType[] roles = values();
        if (type >= roles.length || type < 0) {
            throw new IllegalArgumentException("Invalid type id");
        }
        return roles[type];
    }

}
