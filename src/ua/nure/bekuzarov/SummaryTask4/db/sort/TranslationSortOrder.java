package ua.nure.bekuzarov.SummaryTask4.db.sort;

/**
 * A sort order that generates order value depending on locale.
 */
public class TranslationSortOrder extends AbstractSortOrder {

    /**
     * Creates a new {@code TranslationSortOrder} object.
     *
     * @param table table name
     */
    public TranslationSortOrder(String table) {
        super(table, null);
    }

    @Override
    public String getValue(String locale) {
        return getTable() + "_" + locale;
    }

}
