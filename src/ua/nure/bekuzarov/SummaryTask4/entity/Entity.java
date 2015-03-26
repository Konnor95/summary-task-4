package ua.nure.bekuzarov.SummaryTask4.entity;

import ua.nure.bekuzarov.SummaryTask4.annotation.Column;

import java.io.Serializable;

/**
 * Base class for all entity class.
 *
 * @author Dmitry Bekuzarov
 */
public abstract class Entity implements Serializable {

    @Column
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
