package ua.nure.bekuzarov.SummaryTask4.util;

/**
 * Stores a tuple of objects.
 * @param <X> type of first object
 * @param <Y> type of second object
 */
public class Tuple<X, Y> {

    /**
     * First object.
     */
    private final X x;

    /**
     * Second object.
     */
    private final Y y;

    /**
     * Create a new tuple.
     * @param x first object value
     * @param y second object value
     */
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets first object
     * @return first object in tuple
     */
    public X getX() {
        return x;
    }

    /**
     * Gets second object
     * @return second object in tuple
     */
    public Y getY() {
        return y;
    }

}