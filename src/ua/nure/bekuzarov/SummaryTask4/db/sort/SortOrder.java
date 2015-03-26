package ua.nure.bekuzarov.SummaryTask4.db.sort;

/**
 * A shell for sort order parameter.
 */
public interface SortOrder {

    /**
     * Gets the value of the parameter based on {@code locale}
     * @param locale current locale
     * @return the value of the parameter
     */
    String getValue(String locale);

}
