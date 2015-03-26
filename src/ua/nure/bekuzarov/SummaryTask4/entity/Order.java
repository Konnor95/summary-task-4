package ua.nure.bekuzarov.SummaryTask4.entity;

import ua.nure.bekuzarov.SummaryTask4.annotation.Column;

import java.sql.Date;

/**
 * Order class.
 */
@SuppressWarnings("unused")
public class Order extends Entity {

    @Column
    private int type;

    @Column
    private int subscriptionId;

    @Column
    private int bookId;

    @Column
    private Date dueDate;

    @Column
    private float fee;

    /**
     * Gets order type.
     *
     * @return order type
     */
    public OrderType getType() {
        return OrderType.define(type);
    }

    /**
     * Sets order type.
     *
     * @param type order type
     */
    public void setType(OrderType type) {
        this.type = type.ordinal();
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    /**
     * Gets due date.
     *
     * @return due date
     */
    public Date getDueDate() {
        return new Date(dueDate.getTime());
    }

    /**
     * Sets due date.
     *
     * @param dueDate due date to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = new Date(dueDate.getTime());
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

}
