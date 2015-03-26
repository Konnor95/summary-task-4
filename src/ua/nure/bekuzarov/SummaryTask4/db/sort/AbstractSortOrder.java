package ua.nure.bekuzarov.SummaryTask4.db.sort;

/**
 * An abstract sort order. Provides base constructor.
 */
public abstract class AbstractSortOrder implements SortOrder{

    private String table;
    private String column;

    protected AbstractSortOrder(String table, String column) {
        this.table = table;
        this.column = column;
    }

    /**
     * Gets order by value
     * @param locale current locale
     * @return order by value
     */
    public abstract String getValue(String locale);

    protected String getTable() {
        return table;
    }

    protected String getColumn() {
        return column;
    }

}
