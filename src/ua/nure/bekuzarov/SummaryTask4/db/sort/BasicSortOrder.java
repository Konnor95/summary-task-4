package ua.nure.bekuzarov.SummaryTask4.db.sort;

/**
 * A basic sort order. Generates order value regardless of locale.
 */
public class BasicSortOrder extends AbstractSortOrder {

    /**
     * Creates a new {@code BasicSortOrder} object.
     *
     * @param table  table name
     * @param column column  name
     */
    public BasicSortOrder(String table, String column) {
        super(table, column);
    }

    @Override
    public String getValue(String locale) {
        return getTable() + "_" + getColumn();
    }

}
