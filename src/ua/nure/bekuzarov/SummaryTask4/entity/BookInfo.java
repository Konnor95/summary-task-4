package ua.nure.bekuzarov.SummaryTask4.entity;

import ua.nure.bekuzarov.SummaryTask4.annotation.Column;

/**
 * Basic information about the book.
 */
public class BookInfo extends Entity{

    @Column
    private Integer amount;

    @Column
    private Integer inStock;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

}
