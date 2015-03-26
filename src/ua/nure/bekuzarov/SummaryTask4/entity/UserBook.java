package ua.nure.bekuzarov.SummaryTask4.entity;

import ua.nure.bekuzarov.SummaryTask4.annotation.Column;
import ua.nure.bekuzarov.SummaryTask4.annotation.Extract;

import java.util.List;


public class UserBook extends Reader {

    @Column
    private Integer feeSum;

    @Extract
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Integer getFeeSum() {
        return feeSum;
    }

    public void setFeeSum(Integer feeSum) {
        this.feeSum = feeSum;
    }
}
