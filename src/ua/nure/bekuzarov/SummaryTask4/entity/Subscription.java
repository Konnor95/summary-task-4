package ua.nure.bekuzarov.SummaryTask4.entity;

import ua.nure.bekuzarov.SummaryTask4.annotation.Column;

import java.sql.Date;

/**
 * Subscription class.
 *
 * @author Dmitry Bekuzarov
 */
public class Subscription extends Entity {

    @Column
    private int userId;
    @Column
    private Date expirationDate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets subscription expiration date.
     *
     * @return subscription expiration date
     */
    public Date getExpirationDate() {
        return new Date(expirationDate.getTime());
    }

    /**
     * Sets subscription expiration date.
     *
     * @param expirationDate subscription expiration date to set
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = new Date(expirationDate.getTime());
    }

}
